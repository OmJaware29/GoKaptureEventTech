package com.customer.assessment.convertor;

import org.springframework.stereotype.Component;

import com.customer.assessment.entity.Customer;
import com.customer.assessment.model.CustomerDTO;

@Component
public class CustomerConvertor {
	
	public Customer customerDtoToEntity(CustomerDTO customerDTO) {

		Customer customer = new Customer();
		
		if(null != customerDTO.getId()) {
			customer.setId(customerDTO.getId());
		}
		
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(null != customerDTO.getLastName() ? customerDTO.getLastName(): "");
		customer.setStreet(null != customerDTO.getStreet() ? customerDTO.getStreet(): "");
		customer.setAddress(customerDTO.getAddress());
		customer.setCity(customerDTO.getCity());
		customer.setState(customerDTO.getState());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		
		return customer;
	}
	
	public CustomerDTO customerEntityToDto(Customer customer) {

		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setId(customer.getId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setStreet(customer.getStreet());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCity(customer.getCity());
		customerDTO.setState(customer.getState());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhone(customer.getPhone());
		
		return customerDTO;
	}
}
