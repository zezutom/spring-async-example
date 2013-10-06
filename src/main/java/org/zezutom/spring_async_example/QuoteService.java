package org.zezutom.spring_async_example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zezutom.spring_async_example.model.Quote;
import org.zezutom.spring_async_example.model.QuoteResponse;

@Service("stockService")
public class QuoteService {

	public static final String URL = "http://query.yahooapis.com/v1/public/yql?q={qid}{symbol}&format={fmt}&env={env}";
	
	public static final String QID = "select * from yahoo.finance.quotes where symbol in ";
	
	public static final String ENV = "http://datatables.org/alltables.env";
	
	public static final String FMT = "json";
	
	private String[] symbols = new String[] {"YHOO", "AAPL", "GOOG", "MSFT"};
			
	@Autowired
	private RestTemplate template;
	
	public List<Quote> getQuotes() {	
		QuoteResponse response = template.getForObject(URL, QuoteResponse.class, QID, getSymbols(), FMT, ENV);
		return response.getQuotes();
	}
	
	private String getSymbols() {
		StringBuilder sb = new StringBuilder("(");
		
		for (String symbol : symbols) 
			sb.append("\"").append(symbol).append("\"").append(",");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		
		return sb.toString();
	}
}
