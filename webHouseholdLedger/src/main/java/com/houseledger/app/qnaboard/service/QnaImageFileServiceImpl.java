package com.houseledger.app.qnaboard.service;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.houseledger.app.qnaboard.dao.QnaImageFileDAO;
import com.houseledger.app.qnaboard.dto.QnaImageFileDTO;
import com.houseledger.app.qnaboard.dto.UploadQnaImageFileDTO;

@Service("qnaImageFileService")
public class QnaImageFileServiceImpl implements QnaImageFileService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String QNA_IMAGE_REPO = "c:\\Spring_uploaded_file\\qna_board_image_repo";
	private static final String[] ALLOWED_IMAGE_EXTENSION = new String[] {".jpg", ".jpeg", ".gif", ".png", ".bmp"};
	
	@Resource(name = "qnaImageFileDAO")
	QnaImageFileDAO qnaImageFileDAO;
	
	// 파일 유효성 검사 - 파일 사이즈 검사
	public boolean checkUploadFileSize(long fileSize) throws Exception {
		// 파일 크기 - 5MB 이하
		if(fileSize > 5242880) {
			return false; // 파일 크기 5MB보다 큰 경우 false
		}
		return true; // 파일 크기 5MB보다 작은 경우 true
	}
	
	// 파일 유효성 검사 - 확장자 검사
	public boolean checkUploadFileExtension(String extension) throws Exception {
		// 확장자 유효성 검사
		for(int i = 0; 0 < ALLOWED_IMAGE_EXTENSION.length; i++) {
			if(extension.equals(ALLOWED_IMAGE_EXTENSION[i])) {
				return true; // 업로드 할 수 있는 확장자 true
			}
		}
		return false; // 업로드 할 수 없는 확장자 false
	}
	
	// 게시글 이미지 임시 업로드 - Temp
	public QnaImageFileDTO uploadImageFileTemporarily(MultipartFile multiFile, String user_idx) throws Exception {
		
		String originalFileName = multiFile.getOriginalFilename(); // 실제 파일 이름
		String storedFileName = user_idx + "-" + LocalDate.now() + "-" + UUID.randomUUID(); // 저장될 이미지 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 이미지 파일의 확장자
		long fileSize = multiFile.getSize();
		
		if(!(checkUploadFileSize(fileSize) && checkUploadFileExtension(extension))) {
			// 조건 중 하나라도 false면 업로드 X
			return null;
		}
		
		File file = new File(QNA_IMAGE_REPO+"\\"+ storedFileName + extension);
		if(!file.exists()) { //경로상에 파일이 존재하지 않을 경우
			file.getParentFile().mkdirs(); //경로에 해당하는 디렉토리들을 생성
		}
		
		// 임시로 저장된 multipartFile을 실제 파일로 전송
		multiFile.transferTo(file);
		
		// DB에 저장
		UploadQnaImageFileDTO uploadQnaImageFileDTO = new UploadQnaImageFileDTO();
		uploadQnaImageFileDTO.setUser_idx(user_idx);
		uploadQnaImageFileDTO.setPath(QNA_IMAGE_REPO);
		uploadQnaImageFileDTO.setOriginal_file_name(originalFileName);
		uploadQnaImageFileDTO.setStored_file_name(storedFileName);
		uploadQnaImageFileDTO.setExtension(extension);
		uploadQnaImageFileDTO.setFile_size(fileSize);
		uploadQnaImageFileDTO.setState("TEMP");
		qnaImageFileDAO.insertQnaImageFile(uploadQnaImageFileDTO);
		
		// return값 설정
		QnaImageFileDTO qnaImageFileDTO = new QnaImageFileDTO();
		qnaImageFileDTO.setOriginal_file_name(originalFileName);
		qnaImageFileDTO.setStored_file_name(storedFileName);
		qnaImageFileDTO.setExtension(extension);
		
		return qnaImageFileDTO;
	}
	
	// 게시글 이미지 저장(STATE=TEMP -> STORED)
	public void storeQnaImageFile(String user_idx, String content, String board_idx) throws Exception {
		
		// 임시 저장된 이미지(state=TEMP) List 불러옴
		List<HashMap<String, Object>> tempImageList = qnaImageFileDAO.selectTempImagesOfUser(user_idx);
		
		for(int i = 0; i < tempImageList.size(); i++) {
			
			String stored_file_name = (String) tempImageList.get(i).get("STORED_FILE_NAME");
			String qna_image_idx = tempImageList.get(i).get("QNA_IMAGE_IDX").toString();
			
			if(content.contains(stored_file_name)) {
				// 저장된 temp 이미지가 content에 포함되어 있으면 
				// 해당 temp 이미지 state를 'STORED'상태로 변경
				qnaImageFileDAO.updateQnaImageStateToStored(qna_image_idx, board_idx);
			}
		} // ./for문 끝
		
	}
	
	// 저장된(STATE=STORED) 이미지 수정
	public void editStoredQnaImageFile(String board_idx, String content) throws Exception {
		
		// 게시글에 저장된 이미지(state=STORED) List 불러옴
		List<HashMap<String, Object>> storedImageList = qnaImageFileDAO.selectStoredImagesOfBoard(board_idx);
		
		for(int i = 0; i < storedImageList.size(); i++) {
			
			String stored_file_name = (String) storedImageList.get(i).get("STORED_FILE_NAME");
			String qna_image_idx = storedImageList.get(i).get("QNA_IMAGE_IDX").toString();
			
			if(!content.contains(stored_file_name)) {
				// 게시글에 저장된 이미지(state=STORED)가 content에 포함되어 있지 않으면 
				// 해당 이미지 state를 'DELETED'상태로 변경
				qnaImageFileDAO.updateQnaImageStateToDeleted(qna_image_idx);
			}
		} // ./for문 끝
		
	}
}
