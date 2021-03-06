package org.zezutom.spring_async_example.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.zezutom.spring_async_example.model.Quote;

@Service("defaultQuoteService")
public class DefaultQuoteService implements QuoteService {

	private static final Random random = new Random();

	private Map<String, Quote> recentQuotes = new HashMap<String, Quote>();

	@Override
	public List<Quote> getAllQuotes() {		
		List<Quote> quotes = new ArrayList<Quote>();
		
		for (String symbol : SYMBOLS) {
			Quote quote = createQuote(symbol);
			quotes.add(quote);
			recentQuotes.put(symbol, quote);
		}
		return quotes;
	}

	@Override
	public List<Quote> getChangedQuotes() {
		List<Quote> quotes = new ArrayList<Quote>();

		for (String symbol : SYMBOLS) {
			Quote quote = getQuote(symbol);

			// don't add the quote if there is no change to it
			if (quote == recentQuotes.get(symbol))
				continue;

			quotes.add(quote);
			recentQuotes.put(symbol, quote);
		}

		return quotes;
	}

	private Quote getQuote(final String symbol) {
		Quote quote = recentQuotes.get(symbol);

		if (quote == null || !random.nextBoolean())
			quote = createQuote(symbol);

		return quote;
	}

	private Quote createQuote(final String symbol) {
		Quote quote = new Quote();
		quote.symbol = symbol;
		quote.ask = getRandomPrice();
		quote.bid = getRandomPrice();
		quote.change = getPriceChange(quote);
		quote.lastTradeTime = getLastTradeTime();

		return quote;
	}

	private String getRandomPrice() {
		return number2String(Math.random() * 100, "00.00");
	}

	private String getPriceChange(Quote quote) {
		double change = 0d;
		Quote previousQuote = recentQuotes.get(quote.symbol);

		if (previousQuote != null)
			change = string2Number(quote.bid)
					- string2Number(previousQuote.bid);

		return (change == 0d) ? "-" : number2String(change, "+#,##00.00;-#");
	}

	private String getLastTradeTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	private String number2String(final double value, final String format) {
		return new DecimalFormat(format).format(value);
	}

	private double string2Number(String value) {
		return Double.valueOf(value);
	}
}
