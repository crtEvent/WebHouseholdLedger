package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.qnaboard.dto.QnaPostDTO;
import com.houseledger.app.qnaboard.dto.QnaSelectListDTO;
import com.houseledger.app.qnaboard.service.QnaCommentService;
import com.houseledger.app.qnaboard.service.QnaFileService;
import com.houseledger.app.qnaboard.service.QnaListService;
import com.houseledger.app.qnaboard.service.QnaPostService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaBoardController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="qnaListService")
	QnaListService qnaListService;
	
	@Resource(name="qnaPostService")
	QnaPostService qnaPostservice;
	
	@Resource(name="qnaCommentService")
	QnaCommentService qnaCommentService;
	
	@Resource(name="qnaFileService")
	QnaFileService qnaFileService;
	
	@RequestMapping(value="/qna/list.do")
	public String qna_list(Model model, QnaSelectListDTO qnaSelectListDTO) throws Exception {
		
		model.addAttribute("pagingDTO", qnaListService.getPaging(qnaSelectListDTO));
		model.addAttribute("qnaPostList", qnaListService.getQnaPostList(qnaSelectListDTO));
		
		return "/qna_board/qna_board_list";
	}
	
	@RequestMapping(value="/qna/myPostList.do")
	public String qna_my_post(Model model, QnaSelectListDTO qnaSelectListDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		qnaSelectListDTO.setSearch_type("my");
		qnaSelectListDTO.setKeyword(userVO.getUser_idx());
		
		model.addAttribute("pagingDTO", qnaListService.getPaging(qnaSelectListDTO));
		model.addAttribute("qnaPostList", qnaListService.getQnaPostList(qnaSelectListDTO));
		
		return "/qna_board/qna_board_list";
	}
	
	@RequestMapping(value="/qna/post.do")
	public String qna_post(Model model, QnaSelectListDTO qnaSelectListDTO, 
			@RequestParam(value="board_idx", required = false) String board_idx) throws Exception {
		
		QnaPostDTO qnaPostDTO = qnaPostservice.getQnaPost(board_idx);
		
		if(qnaPostDTO == null) {
			model.addAttribute("msg","삭제되었거나 없는 게시물입니다.");
			return "/common/redirect";
		}
		
		model.addAttribute("pagingDTO", qnaListService.getPaging(qnaSelectListDTO));
		model.addAttribute("qnaPostList", qnaListService.getQnaPostList(qnaSelectListDTO));
		model.addAttribute("qnaPostDTO", qnaPostDTO);
		model.addAttribute("qnaFileList", qnaFileService.getQnaFileList(board_idx));
		model.addAttribute("qnaCommentList", qnaCommentService.getQnaCommentList(board_idx));
		
		return "/qna_board/qna_board_list";
	}
	
}
