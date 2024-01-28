package com.customer.assessment.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.customer.assessment.convertor.CustomerConvertor;
import com.customer.assessment.entity.Customer;
import com.customer.assessment.model.CustomerDTO;
import com.customer.assessment.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerConvertor customerConvertor;

	@Autowired
	CustomerRepository customerRepository;

	private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher.matchingAny()
			.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
			.withMatcher("city", ExampleMatcher.GenericPropertyMatchers.contains().exact())
			.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().exact())
			.withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains().exact())
			.withIgnorePaths("id", "lastName", "state", "street", "address");

	@Override
	public void save(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		Customer customer = customerConvertor.customerDtoToEntity(customerDTO);
		customerRepository.save(customer);
	}

	@Override
	public void update(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		if (null != customerDTO.getId()) {
			customerRepository.save(customerConvertor.customerDtoToEntity(customerDTO));
		}
	}

	@Override
	public List<CustomerDTO> fetchAll(Integer pageNo, Integer pageSize, String sortBy, String searchBy,
			String searchValue) {
		// TODO Auto-generated method stub

		List<CustomerDTO> customerDTOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Customer customer = new Customer();
		Page<Customer> pagedResult;
		if (null != searchBy && null != searchValue) {
			switch (searchBy) {
			case "firstName":
				customer.setFirstName(searchValue);
			case "city":
				customer.setCity(searchValue);
			case "email":
				customer.setEmail(searchValue);
			case "phone":
				customer.setPhone(searchValue);
			}
			Example<Customer> example = Example.of(customer, SEARCH_CONDITIONS_MATCH_ANY);
			pagedResult = customerRepository.findAll(example, paging);
		} else {
			pagedResult = customerRepository.findAll(paging);
		}

		if (pagedResult.hasContent()) {
			customerDTOs = pagedResult.getContent().stream().map(cust -> customerConvertor.customerEntityToDto(cust))
					.collect(Collectors.toList());
			return customerDTOs;
		}
		return customerDTOs;
	}

	@Override
	public CustomerDTO fetch(int id) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(id).orElse(null);

		if (null == customer)
			throw new IllegalStateException("Customer not found");

		return customerConvertor.customerEntityToDto(customer);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(id).orElse(null);

		if (null == customer)
			throw new IllegalStateException("Customer not found");

		customerRepository.delete(customer);
	}

	@Override
	public Map<String, String> searchOptions() {
		// TODO Auto-generated method stub
		Map<String, String> searchMap = new LinkedHashMap<>();
		searchMap.put("First Name", "firstName");
		searchMap.put("City", "city");
		searchMap.put("email", "email");
		searchMap.put("phone", "phone");

		return searchMap;
	}

}
