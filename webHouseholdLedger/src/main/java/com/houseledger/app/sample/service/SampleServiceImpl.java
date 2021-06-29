package com.houseledger.app.sample.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houseledger.app.sample.dao.SampleDAO;



@Service("sampleService")
public class SampleServiceImpl {

	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	public String getTime() throws Exception {
		return sampleDAO.getTime();
	}
	
}
