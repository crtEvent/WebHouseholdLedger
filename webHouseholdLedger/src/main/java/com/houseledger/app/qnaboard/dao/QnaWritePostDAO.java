package com.houseledger.app.qnaboard.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;

@Repository("qnaWritePostDAO")
public class QnaWritePostDAO extends AbstractDAO{
	
	public void insertQnaPost(WriteQnaPostDTO writeQnaPostDTO) throws Exception {
		insert("qnaWritePost.insertQnaPost", writeQnaPostDTO);
	}

}
