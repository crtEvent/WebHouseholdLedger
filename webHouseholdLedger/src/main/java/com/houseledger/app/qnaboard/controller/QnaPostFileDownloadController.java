package com.houseledger.app.qnaboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.houseledger.app.qnaboard.dto.QnaFileDTO;
import com.houseledger.app.qnaboard.service.QnaFileService;

@Controller
public class QnaPostFileDownloadController {
	
	private static final String QNA_ATTACHMENTS_REPO = "c:\\Spring_uploaded_file\\qna_board_attachments_repo";
	
	@Resource(name = "qnaFileService")
	QnaFileService qnaFileService;
	
	@RequestMapping(value="/qna/file_download.do")
	public void qna_file_download(@RequestParam("qna_file_idx") String qna_file_idx, 
			HttpServletResponse response) throws Exception {
		
		QnaFileDTO qnaFileDTO = qnaFileService.getQnaFile(qna_file_idx);
		
		// 실제 파일 이름 - UTF-8 인코딩
		String originalFileNmae = new String(qnaFileDTO.getOriginal_file_name().getBytes("UTF-8"), "ISO-8859-1");
		
		OutputStream out = response.getOutputStream();
		String downloadFile = QNA_ATTACHMENTS_REPO + "\\" + qnaFileDTO.getStored_file_name() + qnaFileDTO.getExtension();
		File file = new File(downloadFile);
		
		response.setHeader("Cashe-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + originalFileNmae);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			int count = in.read(buffer);
			if(count == -1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		
		in.close();
		out.close();
	}
	
}
