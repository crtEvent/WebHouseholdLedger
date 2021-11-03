package com.houseledger.app.qnaboard.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.QnaUploadFileDTO;

@Repository("qnaFileDAO")
public class QnaFileDAO extends AbstractDAO{
	
	public void insertQnaFile(QnaUploadFileDTO qnaUploadFileDTO) throws Exception {
		insert("qnaFile.insertQnaFile", qnaUploadFileDTO);
	}

}
