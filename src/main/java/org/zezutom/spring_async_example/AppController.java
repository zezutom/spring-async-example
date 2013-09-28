package org.zezutom.spring_async_example;

import java.util.concurrent.Callable;

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
	
	@RequestMapping(method=RequestMethod.GET, value="/stats")
	public @ResponseBody Callable<SystemStats> stats() {
		return new Callable<SystemStats>() {
			@Override
			public SystemStats call() throws Exception {
				Thread.sleep(2000);
				return new SystemStats();
			}
		};
	}

}
