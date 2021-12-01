package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.houseledger.app.qnaboard.dto.EditQnaPostDTO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;
import com.houseledger.app.qnaboard.service.QnaEditPostService;
import com.houseledger.app.qnaboard.service.QnaFileService;
import com.houseledger.app.qnaboard.service.QnaImageFileService;
import com.houseledger.app.qnaboard.service.QnaPostValidationService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaPostEditController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name= "qnaPostValidationService")
	QnaPostValidationService qnaPostValidationService;

	@Resource(name = "qnaEditPostService")
	QnaEditPostService qnaEditPostService;
	
	@Resource(name="qnaFileService")
	QnaFileService qnaFileService;
	
	@Resource(name = "qnaImageFileService")
	QnaImageFileService qnaImageFileService;
	
	@RequestMapping(value="/qna/editPost.do")
	public String qna_edit(Model model, 
			@RequestParam("board_idx") String board_idx, 
			@SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		if(qnaEditPostService.checkWriterUserIdx(board_idx, userVO.getUser_idx())) {
			// [게시글 작성자]와 [게시글 수정 페이지에 접근하려는 유저]가 같을 경우
			QnaPostDTO qnaPostDTO = qnaEditPostService.getQnaPostForEdit(board_idx);
			
			if(qnaPostDTO != null) {
				// 게시글이 존재하는 경우 -> 게시글 수정 페이지로 이동 [정상적인 제어 경로]
				model.addAttribute("qnaPostDTO", qnaPostDTO);
				model.addAttribute("qnaFileList", qnaFileService.getQnaFileList(board_idx));
				return "/qna_board/qna_post_edit";
			} else {
				// 게시글이 존재하지 않는 경우
				model.addAttribute("msg","삭제되었거나 없는 게시물입니다.");
			}
			
		} else {
			// [게시글 작성자]와 [게시글 수정 페이지에 접근하려는 유저]가 다른 경우
			model.addAttribute("msg","게시글 작성자가 아닙니다.");
		}
		 
		return "/common/redirect";
	}
	
	@RequestMapping(value="/qna/update_post.do")
	public String update_qna_post(EditQnaPostDTO editQnaPostDTO, 
			@SessionAttribute("userSession")UserVO userVO, 
			MultipartHttpServletRequest multipartRequest, 
			Model model) throws Exception {
		
		if(!qnaPostValidationService.checkVaildQnaPost(editQnaPostDTO.getSubject(), editQnaPostDTO.getContent())) {
			// [제목 5글자 이상 & 내용 2000자 이하]가 아니면
			// 이전 페이지로 이동
			model.addAttribute("board_idx", editQnaPostDTO.getBoard_idx());
			return "forward:/qna/editPost.do";
		}
		
		// 게시글 UPDATE
		qnaEditPostService.editQnaPost(editQnaPostDTO);
		
		// 기존 이미지 파일 처리
		qnaImageFileService.editStoredQnaImageFile(editQnaPostDTO.getBoard_idx(), editQnaPostDTO.getContent());
		
		// 새 이미지 파일 처리
		qnaImageFileService.storeQnaImageFile(userVO.getUser_idx(), editQnaPostDTO.getContent(), editQnaPostDTO.getBoard_idx());
		
		// 기존 첨부파일 분류
		qnaFileService.editPreviouslyUploadFiles(multipartRequest, editQnaPostDTO.getBoard_idx());
		
		// 신규 첨부파일 업로드
		qnaFileService.uploadAttachedFilesToQnaPost(multipartRequest, editQnaPostDTO.getBoard_idx(), userVO.getUser_idx());

		return "redirect:/qna/post.do?board_idx="+editQnaPostDTO.getBoard_idx();
	}
}
