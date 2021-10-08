package com.houseledger.app.qnaboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.QnaSelectListDTO;

@Repository("qnaListDAO")
public class QnaListDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectQnaPostList(QnaSelectListDTO qnaSelectListDTO) throws Exception {
		return (List<Map<String, Object>>) selectList("qnaBoard.selectQnaPostList", qnaSelectListDTO);
	}

}
