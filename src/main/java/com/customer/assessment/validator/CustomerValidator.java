package com.customer.assessment.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.customer.assessment.model.CustomerDTO;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CustomerDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CustomerDTO p = (CustomerDTO) target;
		ValidationUtils.rejectIfEmpty(errors, "firstName", "name.empty");
		ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(errors, "city", "city.empty");
		ValidationUtils.rejectIfEmpty(errors, "state", "state.empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "address.empty");
		
	}

}
