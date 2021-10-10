package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseledger.app.qnaboard.dto.QnaSelectListDTO;
import com.houseledger.app.qnaboard.service.QnaListServiceImpl;

@Controller
public class QnaBoardController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="qnaListService")
	QnaListServiceImpl qnaListService;
	
	@RequestMapping(value="/qna/list.do")
	public String qna_list(Model model, QnaSelectListDTO qnaSelectListDTO) throws Exception {
		
		model.addAttribute("pagingDTO", qnaListService.getPaging(qnaSelectListDTO));
		model.addAttribute("qnaPostList", qnaListService.getQnaPostList(qnaSelectListDTO));
		
		return "/qna_board/qna_board_list";
	}

}
