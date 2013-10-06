package org.zezutom.spring_async_example.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Quote {
	
	@JsonProperty("Symbol")
	public String symbol;
	
	@JsonProperty("BidRealtime")
	public String bid;
	
	@JsonProperty("AskRealtime")
	public String ask;
	
	@JsonProperty("ChangeRealtime")
	public String change;

	@JsonProperty("LastTradeTime")
	public String lastTradeTime;
		
}
