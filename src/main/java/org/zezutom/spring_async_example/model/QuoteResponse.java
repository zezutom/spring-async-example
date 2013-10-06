package org.zezutom.spring_async_example.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse {

	@JsonProperty("query")
	public Results results;
	
	public List<Quote> getQuotes() {
		if (results == null || results.quoteList == null)
			return new ArrayList<Quote>();
			
		return results.quoteList.values;
	}
}
