package com.customer.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.customer.assessment.model.CustomerDTO;
import com.customer.assessment.model.LoginDTO;
import com.customer.assessment.service.CustomerService;

@Controller
public class LoginController {
    
    @Autowired
    CustomerService customerService;

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher.matchingAll()
			.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
			.withMatcher("password", ExampleMatcher.GenericPropertyMatchers.contains().exact())
			.withIgnorePaths("user_id");

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		LoginDTO loginDTO = new LoginDTO();
		model.addObject("loginDTO", loginDTO);
		model.setViewName("login");

		return model;
	}

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView showWelcomePage(@ModelAttribute("loginDTO") LoginDTO loginDTO, final BindingResult result){

    	ModelAndView modelAndView = new ModelAndView();
    	
        boolean isValidUser = null != loginDTO && (null != loginDTO.getUsername() && !loginDTO.getUsername().equals("admin")) && (null != loginDTO.getPassword() && !loginDTO.getPassword().equals("champ@123"));

        if (!isValidUser) {
        	modelAndView.addObject("loginDTO", loginDTO);
        	modelAndView.addObject("error", "User Does not exist");
    		modelAndView.setViewName("login");
    		return modelAndView;
        }

        modelAndView.setViewName("redirect:/list");
		modelAndView.addObject("searchBy", customerService.searchOptions());
		return modelAndView;
    }
}
