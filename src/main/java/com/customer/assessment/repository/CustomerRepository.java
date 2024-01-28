package com.customer.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.assessment.entity.Customer;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	Optional<Customer> findById(Integer id);
	
	@Query("DELETE FROM Customer c WHERE c.id = ?1")
	void deleteById(Integer id);
}
