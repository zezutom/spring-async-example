package org.zezutom.spring_async_example.web;

import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zezutom.spring_async_example.model.Quote;
import org.zezutom.spring_async_example.service.QuoteService;

@Controller
public class AppController {
	
	@Resource(name="defaultQuoteService")
	private QuoteService quoteService;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("quotes", quoteService.getAllQuotes());
		return "index";
	}
	
	@RequestMapping(value="quotes/get", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Callable<List<Quote>> getQuotes() {
		return new Callable<List<Quote>>() {
			@Override
			public List<Quote> call() throws Exception {
				return quoteService.getChangedQuotes();
			}
		};
	}
	
}
