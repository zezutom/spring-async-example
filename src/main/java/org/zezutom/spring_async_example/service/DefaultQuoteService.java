package org.zezutom.spring_async_example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zezutom.spring_async_example.model.Quote;

@Service("defaultQuoteService")
public class DefaultQuoteService implements QuoteService {

	// TODO - keep the previously returned quotes
	
	@Override
	public List<Quote> getQuotes() {
		List<Quote> quotes = new ArrayList<>();
		
		for (String symbol : SYMBOLS) {
			Quote quote = new Quote();
			quote.symbol = symbol;
			quote.ask = getRandomPrice();
			quote.bid = getRandomPrice();
			quote.change = getPriceChange();
			quote.lastTradeTime = getLastTradeTime();
			quotes.add(quote);
		}
		return quotes;
	}

	private String getRandomPrice() {
		// TODO - random price
		return "10";
	}
	
	private String getPriceChange() {
		// TODO - diff between the new and the last price
		return "+0.15";
	}
	
	private String getLastTradeTime() {
		// TODO - now (hh:mm)
		return "20:01";
	}
}
