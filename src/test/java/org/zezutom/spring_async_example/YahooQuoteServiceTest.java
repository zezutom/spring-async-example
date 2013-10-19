package org.zezutom.spring_async_example;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.zezutom.spring_async_example.model.Quote;
import org.zezutom.spring_async_example.service.YahooQuoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestConfig.class })
public class YahooQuoteServiceTest {

	@Autowired
	private YahooQuoteService quoteService;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Before
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getAllQuotes() throws IOException {
		mockServer
				.expect(requestTo(getQuotesUrl()))
				.andExpect(method(HttpMethod.GET))
				.andRespond(
						withSuccess(getJson("quotes.json"),
								MediaType.APPLICATION_JSON));

		List<Quote> quotes = quoteService.getAllQuotes();
		assertNotNull(quotes);
		assertTrue(quotes.size() == 4);

		assertQuote(quotes.get(0), "YHOO", "32.37", "34.75", "+1.015", "4:00pm");
		assertQuote(quotes.get(1), "AAPL", "482.40", "483.60", "-0.38", "4:00pm");
		assertQuote(quotes.get(2), "GOOG", "869.51", "878.50", "-3.7401", "4:00pm");
		assertQuote(quotes.get(3), "MSFT", "0.00", "33.96", "+0.02", "4:00pm");
	}

	private void assertQuote(Quote quote, String symbol, String bid, String ask, String change, String lastTradeTime) {
		
	}
	
	private String getQuotesUrl() {
		return "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22,%22AAPL%22,%22GOOG%22,%22MSFT%22)&format=json&env=http://datatables.org/alltables.env";
	}

	private String getJson(String fileName) throws IOException {
		ClassPathResource res = new ClassPathResource(fileName);
		return stream2String(res.getInputStream());
	}

	private String stream2String(InputStream is) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;

		br = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = br.readLine()) != null)
				sb.append(line);
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return sb.toString();
	}

}
