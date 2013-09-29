package org.zezutom.spring_async_example;

import java.util.concurrent.Callable;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
		
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="stats/get", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Callable<SystemStats> asyncStats() {
		return new Callable<SystemStats>() {
			@Override
			public SystemStats call() throws Exception {
				return new SystemStats();
			}
		};
	}
	
}
