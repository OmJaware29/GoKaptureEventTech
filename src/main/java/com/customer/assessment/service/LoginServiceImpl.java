package com.customer.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import com.customer.assessment.entity.Login;
import com.customer.assessment.model.LoginDTO;
import com.customer.assessment.repository.LoginRepository;

public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public void save(LoginDTO LoginDTO) {
		// TODO Auto-generated method stub
		//Login login= loginConvertor.loginDtoToEntity(LoginDTO);
		//loginRepository.save(login);
	}

}
