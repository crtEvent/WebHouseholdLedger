package com.houseledger.app.mail.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.mail.dao.WriteMailDAO;
import com.houseledger.app.mail.dto.WriteMailDTO;

@Service("storeMailService")
public class StoreMailService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "writeMailDAO")
	WriteMailDAO writeMailDAO;
	
	public void storeMailToDB(WriteMailDTO writeMailDTO) throws Exception {
		writeMailDAO.insertMail(writeMailDTO);
	}
}
