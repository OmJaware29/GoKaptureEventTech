package com.customer.assessment;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
//@Configuration
public class CustomerAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAssessmentApplication.class, args);
	}

//	@Bean(name="messageSource")
//	public MessageSource messageSource() {
//	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
////	    Locale.setDefault(Locale.getDefault());
//	    messageSource.setBasename("classpath:messages");
//	    messageSource.setDefaultEncoding("UTF-8");
//	    return messageSource;
//	}
}
