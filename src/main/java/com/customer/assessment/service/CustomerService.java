package com.customer.assessment.service;

import java.util.List;
import java.util.Map;

import com.customer.assessment.entity.Customer;
import com.customer.assessment.model.CustomerDTO;

public interface CustomerService {

	public void save(CustomerDTO customerDTO);

	public void update(CustomerDTO customerDTO);
	
	public List<CustomerDTO> fetchAll(Integer pageNo, Integer pageSize, String sortBy, String searchBy, String searchValue);
	
	public CustomerDTO fetch(int id);
	
	public void delete(int id);
	
	public Map<String, String> searchOptions();
}
