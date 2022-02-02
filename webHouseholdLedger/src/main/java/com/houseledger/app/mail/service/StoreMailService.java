package com.houseledger.app.mail.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.mail.dao.InsertMailDAO;
import com.houseledger.app.mail.dto.WriteMailDTO;

@Service("storeMailService")
public class StoreMailService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "insertMailDAO")
	InsertMailDAO insertMailDAO;
	
	public void storeSentMailToDB(WriteMailDTO writeMailDTO) throws Exception {
		writeMailDTO.setMail_state("SENT");
		insertMailDAO.insertMail(writeMailDTO);
	}
	
	public void storeMailFormToDB(WriteMailDTO writeMailDTO) throws Exception {
		writeMailDTO.setMail_to("");
		writeMailDTO.setMail_state("STORED");
		insertMailDAO.insertMail(writeMailDTO);
	}
	
}
