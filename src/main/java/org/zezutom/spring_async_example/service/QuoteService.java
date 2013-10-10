package org.zezutom.spring_async_example.service;

import java.util.List;

import org.zezutom.spring_async_example.model.Quote;

public interface QuoteService {
	
	public static final String[] SYMBOLS = new String[] {"YHOO", "AAPL", "GOOG", "MSFT"};
	
	List<Quote> getQuotes();
}
