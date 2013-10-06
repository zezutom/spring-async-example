package org.zezutom.spring_async_example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zezutom.spring_async_example.model.Quote;

@Controller
public class AppController {
	
	@Autowired
	private QuoteService quoteService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="quotes/get", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Quote> getQuote() {
		return quoteService.getQuotes();
	}
	
}
