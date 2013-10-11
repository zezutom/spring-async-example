package org.zezutom.spring_async_example;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zezutom.spring_async_example.service.DefaultQuoteService;
import org.zezutom.spring_async_example.service.YahooQuoteService;

public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public YahooQuoteService yahooQuoteService() {
		return new YahooQuoteService();
	}
	
	@Bean
	public DefaultQuoteService defaultQuoteService() {
		return new DefaultQuoteService();
	}
}
