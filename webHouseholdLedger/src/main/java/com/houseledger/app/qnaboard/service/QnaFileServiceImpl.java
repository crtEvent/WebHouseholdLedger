package com.houseledger.app.qnaboard.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.houseledger.app.qnaboard.dao.QnaFileDAO;
import com.houseledger.app.qnaboard.dto.QnaFileDTO;
import com.houseledger.app.qnaboard.dto.UploadQnaFileDTO;

@Service("qnaFileService")
public class QnaFileServiceImpl implements QnaFileService {
	
	private static final String QNA_ATTACHMENTS_REPO = "c:\\Spring_uploaded_file\\qna_board_attachments_repo";
	
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
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 이미지 파일의 확장자
			String storedFileName = user_idx + "-" + UUID.randomUUID(); // 저장될 이미지 파일명
			
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
				uploadQnaFileDTO.setFile_size(multiFile.getSize());
				
				qnaFileDAO.insertQnaFile(uploadQnaFileDTO);
			} // /.if문
		}// /.while문
		
		
	}
	
	// 게시글의 파일 리스트 가져오기
	public List<HashMap<String, Object>> getQnaFileList(String board_idx) throws Exception {
		return qnaFileDAO.selectQnaFileList(board_idx);
	}
	
	// 다운로드 할 게시글의 파일 정보 가져오기
	public QnaFileDTO getQnaFile(String qna_file_idx) throws Exception {
		return qnaFileDAO.selectQnaFile(qna_file_idx);
	}
	
	

}
