package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.houseledger.app.qnaboard.dto.QnaImageFileDTO;
import com.houseledger.app.qnaboard.service.QnaImageFileService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaImageUploadController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaImageFileService")
	QnaImageFileService qnaImageFileService;
	
	@PostMapping(value="/qna/uploadImageFile.do")
	@ResponseBody
	public QnaImageFileDTO upload_qna_image_file(@RequestParam("file") MultipartFile multiFile,
			@SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		QnaImageFileDTO qnaImageFileDTO = qnaImageFileService.uploadImageFileTemporarily(multiFile, userVO.getUser_idx());
		
		return qnaImageFileDTO;
	}
	
}
