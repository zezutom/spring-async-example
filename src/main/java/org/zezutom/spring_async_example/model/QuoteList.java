package org.zezutom.spring_async_example.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteList {
	
	@JsonProperty("quote")
	public List<Quote> values;
}
