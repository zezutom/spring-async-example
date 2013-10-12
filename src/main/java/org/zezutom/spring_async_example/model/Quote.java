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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ask == null) ? 0 : ask.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result
				+ ((lastTradeTime == null) ? 0 : lastTradeTime.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		if (ask == null) {
			if (other.ask != null)
				return false;
		} else if (!ask.equals(other.ask))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (change == null) {
			if (other.change != null)
				return false;
		} else if (!change.equals(other.change))
			return false;
		if (lastTradeTime == null) {
			if (other.lastTradeTime != null)
				return false;
		} else if (!lastTradeTime.equals(other.lastTradeTime))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
		
}
