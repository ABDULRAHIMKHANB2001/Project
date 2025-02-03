package com.lms.loanplans;

import org.springframework.boot.builder.SpringApplicationBuilder;
// to configure the beans 
 
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 
public class ServletInitializer extends SpringBootServletInitializer {
 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoanplansApplication.class);
	}
 
}
