package com.houseledger.app.mail.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.mail.dto.WriteMailDTO;

@Repository("insertMailDAO")
public class InsertMailDAO extends AbstractDAO {

	public void insertMail(WriteMailDTO writeMailDTO) throws Exception {
		update("insertMail.insertMail", writeMailDTO);
	}
}
