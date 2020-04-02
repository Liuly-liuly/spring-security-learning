/**
 * 
 */
package com.liuly.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.liuly.web.filter.MyFilter;
import com.liuly.web.intercepter.TimerIntercepter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private TimerIntercepter timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
//	@Bean   注册把外面的filter加入自己的工程中
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		MyFilter myFilter = new MyFilter();
		registrationBean.setFilter(myFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
		
	}

}
