package org.zezutom.spring_async_example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zezutom.spring_async_example.model.Quote;
import org.zezutom.spring_async_example.service.DefaultQuoteService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class DefaultQuoteServiceTest {

	@Autowired
	private DefaultQuoteService quoteService;
	
	@Test
	public void getAllQuotes() {
		List<Quote> quotes = quoteService.getAllQuotes();
		
		assertNotNull(quotes);
		for (Quote quote : quotes)
			assertQuote(quote);
	}
	
	private void assertQuote(Quote quote) {
		assertNotNull(quote);		
		assertValue("\\w+", quote.symbol);
		assertValue("\\d{2}.\\d{2}", quote.ask);
		assertValue("\\d{2}.\\d{2}", quote.bid);
		assertValue("\\d{2}:\\d{2}:\\d{2}", quote.lastTradeTime);
	}
	
	private void assertValue(String regex, String value) {
		Pattern pattern = Pattern.compile("^" + regex + "$");
		Matcher matcher = pattern.matcher(value);		
		assertTrue(matcher.matches());
	}	
}
