package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.qnaboard.service.QnaDeletePostService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaPostDeleteController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaDeletePostService")
	QnaDeletePostService qnaDeletePostService;
	
	@RequestMapping("/qna/delete_post.do")
	public String qna_delete(Model model, 
			@RequestParam(required = false) String board_idx, 
			@SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		// board_idx가 없는 경우
		if(board_idx == null || board_idx.isEmpty()) {
			model.addAttribute("msg","잘못된 접근입니다.");
			return "/common/redirect";
		}
		
		// 게시글 글쓴이와 게시글을 삭제하려는 유저가 다를 경우
		if(!qnaDeletePostService.selectUserIdxOfPost(board_idx).equals(userVO.getUser_idx())) {
			model.addAttribute("msg","게시글 작성자가 아닙니다.");
			return "/common/redirect";
		}
		
		// 게시글 삭제 처리
		qnaDeletePostService.deleteQnaPost(board_idx);
		
		return "redirect:/qna/list.do";
	}
	
}
