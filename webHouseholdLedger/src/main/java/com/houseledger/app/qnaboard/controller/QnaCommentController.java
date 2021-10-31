package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.qnaboard.dto.WriteQnaCommentDTO;
import com.houseledger.app.qnaboard.service.QnaCommentService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaCommentController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="qnaCommentService")
	QnaCommentService qnaCommentService;
	
	@RequestMapping(value="/qna/write_comment.do")
	public String write_comment(HttpServletRequest request, @SessionAttribute("userSession")UserVO userVO, WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		
		writeQnaCommentDTO.setUser_idx(userVO.getUser_idx());
		
		qnaCommentService.writeQnaComment(writeQnaCommentDTO);
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	@RequestMapping(value="/qna/write_recomment.do")
	public String write_recomment(HttpServletRequest request, @SessionAttribute("userSession")UserVO userVO, WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		
		writeQnaCommentDTO.setUser_idx(userVO.getUser_idx());
		
		qnaCommentService.writeQnaReComment(writeQnaCommentDTO);
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
}
