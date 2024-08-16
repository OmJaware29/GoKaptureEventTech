package com.customer.assessment.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.customer.assessment.model.CustomerDTO;
import com.customer.assessment.repository.CustomerRepository;
import com.customer.assessment.service.CustomerService;
import com.customer.assessment.validator.CustomerValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Controller
//@RequestMapping(value ="/dataingestion/customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerValidator customerValidator;

	@Autowired
	EntityManager entityManager;

	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	public ModelAndView sync() throws URISyntaxException, JsonMappingException, JsonProcessingException {

		//POST URL to get AuthToken for validation
		URI uri = new URI("https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp");
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{ \"login_id\" : \"test@sunbasedata.com\", \"password\" : \"Test@123\" }";
		JsonNode jsonNode = objectMapper.readTree(json);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonNode, String.class);
		String accessToken = objectMapper.readTree(result.getBody()).get("access_token").asText();

		//GET URL to access list of customers
		org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.set("Authorization", "Bearer " + accessToken);
		HttpEntity request = new HttpEntity(httpHeaders);
		String resourceURL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd={cmd}";
		Map<String, String> params = Collections.singletonMap("cmd", "get_customer_list");
		ResponseEntity<List> response = restTemplate.exchange(resourceURL, HttpMethod.GET, request, List.class, params);
		List customerSyncDTOs = response.getBody();

		//Save Or Update the records accordingly.
		if (!CollectionUtils.isEmpty(customerSyncDTOs)) {
			for (int i = 0; i < customerSyncDTOs.size(); i++) {
				LinkedHashMap<String, String> tuple = (LinkedHashMap<String, String>) customerSyncDTOs.get(i);

				String queryStr = "select c.customer_id from customer c where" + " c.email='" + tuple.get("email")
						+ "' AND c.first_name='" + tuple.get("first_name") + "' AND c.last_name='" 
						+ tuple.get("last_name") + "' AND c.city='" + tuple.get("city")
						+ "' AND c.state='" + tuple.get("state") + "' AND c.phone='" + tuple.get("phone") + "'";
				Query query = entityManager.createNativeQuery(queryStr);
				List rows = (List) query.getResultList();
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setFirstName(tuple.get("first_name"));
				customerDTO.setLastName(tuple.get("last_name"));
				customerDTO.setStreet(tuple.get("street"));
				customerDTO.setAddress(tuple.get("address"));
				customerDTO.setCity(tuple.get("city"));
				customerDTO.setState(tuple.get("state"));
				customerDTO.setEmail(tuple.get("email"));
				customerDTO.setPhone(tuple.get("phone"));
				if (rows.size() > 0) {

				 	Integer record = (Integer) rows.get(0);
					customerDTO.setId(record);
					customerService.update(customerDTO);
				} else {
					
					customerService.save(customerDTO);
				}
			}
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/list");
		model.addObject("searchBy", customerService.searchOptions());
		return model;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "firstName") String sortBy, @RequestParam(required = false) String searchBy,
			@RequestParam(required = false) String searchValue) {

		ModelAndView model = new ModelAndView("customer/customer_list");
		List<CustomerDTO> customerDTOs = customerService.fetchAll(pageNo, pageSize, sortBy, searchBy, searchValue);
		model.addObject("customerDTOs", customerDTOs);
		model.addObject("searchBy", customerService.searchOptions());
		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView addCustomer() {

		ModelAndView model = new ModelAndView();
		CustomerDTO customerDTO = new CustomerDTO();
		model.addObject("customerDTO", customerDTO);
		model.setViewName("customer/add_customer");

		return model;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateCustomer(@PathVariable int id) {
		ModelAndView model = new ModelAndView();

		CustomerDTO customerDTO = customerService.fetch(id);
		customerDTO.setOps("u");
		model.addObject("customerDTO", customerDTO);
		model.setViewName("customer/add_customer");

		return model;
	}

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("customerDTO") CustomerDTO customerDTO, final BindingResult result) {

		ModelAndView modelAndView = new ModelAndView();
		customerValidator.validate(customerDTO, result);

		if (!result.hasErrors()) {
			if (null != customerDTO.getOps() && customerDTO.equals("u")) {
				customerService.update(customerDTO);
			} else {
				customerService.save(customerDTO);
			}

			modelAndView.setViewName("redirect:/list");
			modelAndView.addObject("searchBy", customerService.searchOptions());
			return modelAndView;
		}
		modelAndView.addObject("customerDTO", customerDTO);
		modelAndView.setViewName("customer/add_customer");
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		customerService.delete(id);
		modelAndView.addObject("searchBy", customerService.searchOptions());
		modelAndView.setViewName("redirect:/list");

		return modelAndView;

	}

}
