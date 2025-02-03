package com.lms.loanplans.applicationconfiguration;

import org.modelmapper.ModelMapper;
//model mapper is a library to convert Entity to DTO and vice versa
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration 
{
	@Bean
    public ModelMapper modelMapper() 
	{
        return new ModelMapper();
    }
}