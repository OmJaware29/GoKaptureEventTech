package com.customer.assessment.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.customer.assessment.entity.Login;
import com.customer.assessment.model.LoginDTO;
import com.customer.assessment.repository.LoginRepository;
import com.customer.assessment.service.CustomerService;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginRepository repo;

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher.matchingAll()
            .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
            .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("user_id");

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("loginDTO", new LoginDTO());
        model.setViewName("login");
        return model;
    }

    @PostMapping("/login")
    public ModelAndView showWelcomePage(@ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        // Simple validation check
        if (loginDTO == null || loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
            modelAndView.addObject("error", "Invalid login details");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        // Query the repository to validate the user
        boolean isValidUser = repo.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()) != null;

        if (!isValidUser) {
            modelAndView.addObject("loginDTO", loginDTO);
            modelAndView.addObject("error", "User does not exist");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/list");
        modelAndView.addObject("searchBy", customerService.searchOptions());
        return modelAndView;
    }

    @PostMapping("/saveLogin")
    public String saveLogin(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        Login loginEntity = new Login();
        BeanUtils.copyProperties(loginDTO, loginEntity);
        repo.save(loginEntity);
        return "login";
    }

    @GetMapping("/loadLogin")
    public String loadLogin(Model model) {
        model.addAttribute("log", new Login());
        return "registerLogin";
    }

    @GetMapping("/saveUser")
    public ModelAndView saveUser() {
        ModelAndView model = new ModelAndView();
        model.addObject("loginDTO", new LoginDTO());
        model.setViewName("login/saveuser");
        return model;
    }
}
