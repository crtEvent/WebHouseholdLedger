package com.houseledger.app.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleConroller {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/sample/testLogger.do")
	public String test_logger(Model model) throws Exception{
		
		log.debug("LoggerInterceptor 실행");
		
		return "";
	}

}
