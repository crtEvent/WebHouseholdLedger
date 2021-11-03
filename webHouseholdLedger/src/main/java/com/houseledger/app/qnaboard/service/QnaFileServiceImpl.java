package com.houseledger.app.qnaboard.service;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.houseledger.app.qnaboard.dao.QnaFileDAO;
import com.houseledger.app.qnaboard.dto.QnaUploadFileDTO;

@Service("qnaFileService")
public class QnaFileServiceImpl implements QnaFileService {
	
	private static final String QNA_ATTACHMENTS_REPO = "c:\\Spring_uploaded_file\\qna_board_attachments_repo";
	
	@Resource(name = "qnaFileDAO")
	QnaFileDAO qnaFileDAO;
	
	public void uploadAttachedFilesToQnaPost(MultipartHttpServletRequest multipartRequest, String board_idx, String user_idx) throws Exception {
		
		QnaUploadFileDTO qnaUploadFileDTO = new QnaUploadFileDTO();
		qnaUploadFileDTO.setBoard_idx(board_idx);
		qnaUploadFileDTO.setUser_idx(user_idx);
		qnaUploadFileDTO.setPath(QNA_ATTACHMENTS_REPO);
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 첨부된 파일들의 이름을 가져옴
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		// 파일 전송(저장)
		while(fileNames.hasNext()){
			String fileName = fileNames.next(); // front단에서 받아온 input[type=file]의 name
			MultipartFile multiFile = multipartRequest.getFile(fileName); // 업로드 된 파일을 MultipartFile 객체에 임시 저장
			String originalFileName = multiFile.getOriginalFilename(); // 실제 파일 이름
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 이미지 파일의 확장자
			String storedFileName = "[" + user_idx + "]" + UUID.randomUUID() + extension; // 저장될 이미지 파일명
			
			File file = new File(QNA_ATTACHMENTS_REPO+"\\"+ fileName);
			
			if(multiFile.getSize()!=0){ // file이 null이 아닌 경우
				if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 해당 경로에 빈 파일(empty file) 생성
					}
				}
				// 임시로 저장된 multipartFile을 실제 파일로 전송
				multiFile.transferTo(new File(QNA_ATTACHMENTS_REPO+"\\"+ storedFileName));
				// DB에 저장
				qnaUploadFileDTO.setOriginal_file_name(originalFileName);
				qnaUploadFileDTO.setStored_file_name(storedFileName);
				qnaUploadFileDTO.setFile_size(multiFile.getSize());
				
				qnaFileDAO.insertQnaFile(qnaUploadFileDTO);
			} // /.if문
		}// /.while문
		
		
	}
	
	

}
