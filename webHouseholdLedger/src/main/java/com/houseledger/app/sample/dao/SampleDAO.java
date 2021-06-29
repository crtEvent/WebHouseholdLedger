package com.houseledger.app.sample.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{
	
	public String getTime() {
		return (String) selectOne("sample.selectNow");
	}

}
