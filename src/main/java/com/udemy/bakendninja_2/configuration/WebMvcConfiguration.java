package com.udemy.bakendninja_2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.udemy.bakendninja_2.component.RequestTimeInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(requestTimeInterceptor);
	}
}
