package com.houseledger.app.qnaboard.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.UploadQnaImageFileDTO;

@Repository("qnaImageFileDAO")
public class QnaImageFileDAO extends AbstractDAO {
	
	public void insertQnaImageFile(UploadQnaImageFileDTO uploadQnaImageFileDTO) throws Exception {
		insert("qnaImageFile.insertQnaImageFile", uploadQnaImageFileDTO);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectTempImagesOfUser(String user_idx) throws Exception {
		return selectList("qnaImageFile.selectTempImagesOfUser", user_idx);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectStoredImagesOfBoard(String board_idx) throws Exception {
		return selectList("qnaImageFile.selectStoredImagesOfBoard", board_idx);
	}
	
	public void updateQnaImageStateToDeleted(String qna_image_idx) throws Exception {
		update("qnaImageFile.updateQnaImageStateToDeleted", qna_image_idx);
	}
	
	public void updateQnaImageStateToStored(String qna_image_idx, String board_idx) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qna_image_idx", qna_image_idx);
		map.put("board_idx", board_idx);
		update("qnaImageFile.updateQnaImageStateToStored", map);
	}

}
