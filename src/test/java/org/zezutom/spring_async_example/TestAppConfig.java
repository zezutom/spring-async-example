package org.zezutom.spring_async_example;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class TestAppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public QuoteService quoteService() {
		return new QuoteService();
	}
}
