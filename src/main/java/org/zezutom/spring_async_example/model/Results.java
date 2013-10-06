package org.zezutom.spring_async_example.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

	@JsonProperty("results")
	public QuoteList quoteList;
}
