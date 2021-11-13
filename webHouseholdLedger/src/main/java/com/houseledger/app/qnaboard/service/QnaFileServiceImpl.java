package com.houseledger.app.qnaboard.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.houseledger.app.qnaboard.dao.QnaFileDAO;
import com.houseledger.app.qnaboard.dto.QnaFileDTO;
import com.houseledger.app.qnaboard.dto.UploadQnaFileDTO;

@Service("qnaFileService")
public class QnaFileServiceImpl implements QnaFileService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String QNA_ATTACHMENTS_REPO = "c:\\Spring_uploaded_file\\qna_board_attachments_repo";
	private static final String[] ALLOWED_EXTENSION = new String[] {".hwp", ".doc", ".docx", ".ppt", ".pptx", 
	                                       			".xls", ".xlsx", ".txt", ".csv", ".jpg", 
	                                    			".jpeg", ".gif", ".png", ".bmp", ".pdf"};
	
	@Resource(name = "qnaFileDAO")
	QnaFileDAO qnaFileDAO;
	
	// 게시글 insert 시 - 파일 업로드
	public void uploadAttachedFilesToQnaPost(MultipartHttpServletRequest multipartRequest, String board_idx, String user_idx) throws Exception {
		
		UploadQnaFileDTO uploadQnaFileDTO = new UploadQnaFileDTO();
		uploadQnaFileDTO.setBoard_idx(board_idx);
		uploadQnaFileDTO.setUser_idx(user_idx);
		uploadQnaFileDTO.setPath(QNA_ATTACHMENTS_REPO);
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 첨부된 파일들의 이름을 가져옴
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		// 파일 전송(저장)
		while(fileNames.hasNext()){
			String fileName = fileNames.next(); // front단에서 받아온 input[type=file]의 name
			MultipartFile multiFile = multipartRequest.getFile(fileName); // 업로드 된 파일을 MultipartFile 객체에 임시 저장
			String originalFileName = multiFile.getOriginalFilename(); // 실제 파일 이름
			String storedFileName = user_idx + "-" + UUID.randomUUID(); // 저장될 이미지 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 이미지 파일의 확장자
			long fileSize = multiFile.getSize();
			
			if(!(checkUploadFileSize(fileSize) && checkUploadFileExtension(extension))) {
				// 조건 중 하나라도 false면 업로드 X
				continue;
			}
			
			File file = new File(QNA_ATTACHMENTS_REPO+"\\"+ fileName);
				
			if(multiFile.getSize()!=0){ // file이 null이 아닌 경우
				
				if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 해당 경로에 빈 파일(empty file) 생성
					}
				}
				
				// 임시로 저장된 multipartFile을 실제 파일로 전송
				multiFile.transferTo(new File(QNA_ATTACHMENTS_REPO+"\\"+ storedFileName + extension));
				// DB에 저장
				uploadQnaFileDTO.setOriginal_file_name(originalFileName);
				uploadQnaFileDTO.setStored_file_name(storedFileName);
				uploadQnaFileDTO.setExtension(extension);
				uploadQnaFileDTO.setFile_size(fileSize);
					
				qnaFileDAO.insertQnaFile(uploadQnaFileDTO);
			} // /.if문	
		}// /.while문
	}// /.uploadAttachedFilesToQnaPost()
	
	// 파일 유효성 검사
	public boolean checkUploadFileSize(long fileSize) throws Exception {
		// 파일 크기 - 5MB 이하
		if(fileSize > 5242880) {
			return false; // 파일 크기 5MB보다 큰 경우 false
		}
		return true; // 파일 크기 5MB보다 작은 경우 true
	}
	
	// 파일 유효성 검사
	public boolean checkUploadFileExtension(String extension) throws Exception {
		// 확장자 유효성 검사
		for(int i = 0; 0 < ALLOWED_EXTENSION.length; i++) {
			if(extension.equals(ALLOWED_EXTENSION[i])) {
				return true; // 업로드 할 수 있는 확장자 true
			}
		}
		return false; // 업로드 할 수 없는 확장자 false
	}
	
	// 게시글의 파일 리스트 가져오기
	public List<HashMap<String, Object>> getQnaFileList(String board_idx) throws Exception {
		return qnaFileDAO.selectQnaFileList(board_idx);
	}
	
	// 다운로드 할 게시글의 파일 정보 가져오기
	public QnaFileDTO getQnaFile(String qna_file_idx) throws Exception {
		return qnaFileDAO.selectQnaFile(qna_file_idx);
	}
	
	public void editPreviouslyUploadFiles(MultipartHttpServletRequest multipartRequest, String board_idx) throws Exception {
		
		// 게시글에 업로드된 파일 임시 삭제
		qnaFileDAO.deleteAllQnaFilesInPost(board_idx);
		
		String[] savedFileIdxFromJsp = multipartRequest.getParameterValues("savedFile");
		
		if(savedFileIdxFromJsp == null) {
			return;
		}
		
		List<String> savedFileIdxFromDB = qnaFileDAO.selectQnaFileidxList(board_idx);
		
		for(int i = 0; i < savedFileIdxFromDB.size(); i++) {
			String qna_file_idx = savedFileIdxFromDB.get(i);
			for(int a = 0; a < savedFileIdxFromJsp.length; a++) {
				if(qna_file_idx.equals(savedFileIdxFromJsp[a])) {
					// 페이지에서 넘어온 file_idx와 DB에 있는 file_idx가 일치하면 해당 파일을 복원
					qnaFileDAO.restoreQnaFile(qna_file_idx);
				}
			}
		}
		
	}// /.editPreviouslyUploadFiles()

}
