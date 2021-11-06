package com.houseledger.app.qnaboard.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.QnaFileDTO;
import com.houseledger.app.qnaboard.dto.UploadQnaFileDTO;

@Repository("qnaFileDAO")
public class QnaFileDAO extends AbstractDAO{
	
	public void insertQnaFile(UploadQnaFileDTO qnaUploadFileDTO) throws Exception {
		insert("qnaFile.insertQnaFile", qnaUploadFileDTO);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectQnaFileList(String board_idx) throws Exception {
		return (List<HashMap<String, Object>>) selectList("qnaFile.selectQnaFileList", board_idx);
	}
	
	public QnaFileDTO selectQnaFile(String qna_file_idx) throws Exception {
		return (QnaFileDTO) selectOne("qnaFile.selectQnaFile", qna_file_idx);
	}

}
