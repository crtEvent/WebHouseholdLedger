package com.houseledger.app.my.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseledger.app.my.service.EditMyInfoService;
import com.houseledger.app.user.vo.UserVO;

@Controller
@RequestMapping("/my")
public class EditMyInfoController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="editMyInfoService")
	EditMyInfoService editMyInfoService;
	
	// 회원정보 수정 페이지
	@RequestMapping("/myinfo.do")
	public String editMyInfoPage() throws Exception {
		
		return "my/edit_my_info";
	}
	
	// 유저 이미지 변경
	@RequestMapping("/editUserImage.do")
	@ResponseBody
	public String editUserImage(String user_image, HttpServletRequest request) throws Exception {
		
		// userSession 불러오기
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userSession");
		
		// 유저 이미지 변경 & 수정된 이미지 값 받아오기
		String changedUser_image = editMyInfoService.editUserImage(user_image, userVO.getUser_idx());
		
		// userSession 삭제
		session.removeAttribute("userSession");
		
		// 변경된 userSession 생성
		userVO.setUser_image(changedUser_image);
		session.setAttribute("userSession", userVO);
		
		return changedUser_image;
	}
	
	// 아이디 수정
	@RequestMapping("/editUserID.do")
	@ResponseBody
	public String editUserID(@RequestParam String user_id, HttpServletRequest request) throws Exception {
		
		// userSession 불러오기
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userSession");
		
		// 아이디 수정 & 수정된 아이디 값 받아오기
		String changedUser_id = editMyInfoService.editUserId(user_id, userVO.getUser_idx());
		
		// userSession 삭제
		session.removeAttribute("userSession");
		
		// 변경된 userSession 생성
		userVO.setUser_id(changedUser_id);
		session.setAttribute("userSession", userVO);
		
		return changedUser_id;
	}
	
	// 이메일 수정
	@RequestMapping("/editUserEmail.do")
	@ResponseBody
	public String editUserEmail(@RequestParam String user_email, HttpServletRequest request) throws Exception {
		
		// userSession 불러오기
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userSession");
		
		// 이메일 수정 & 수정된 이메일 값 받아오기
		String changedUser_email = editMyInfoService.editUserEmail(user_email, userVO.getUser_idx());
		
		// userSession 삭제
		session.removeAttribute("userSession");
		
		// 변경된 userSession 생성
		userVO.setUser_email(changedUser_email);
		session.setAttribute("userSession", userVO);
		
		return changedUser_email;
	}
	
	// 비밀번호 수정
	@RequestMapping("/editUserPassword.do")
	@ResponseBody
	public boolean editUserPassword(@RequestParam String old_user_password, 
			@RequestParam String new_user_password, 
			HttpServletRequest request) throws Exception {
		
		// userSession 불러오기
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userSession");
		
		// 기존 비밀번호가 일치하면 - 비밀번호 변경 & true 반환
		if(editMyInfoService.checkOldUserPassword(old_user_password, userVO.getUser_idx())) {
			// 비밀번호 변경 & 변경된 비밀번호 값(암호화 된 상태) 받아오기
			String changedUser_password = editMyInfoService.editUserPassword(new_user_password, userVO.getUser_idx());
			
			// userSession 삭제
			session.removeAttribute("userSession");
			
			// 변경된 userSession 생성
			userVO.setUser_password(changedUser_password);
			session.setAttribute("userSession", userVO);
			
			return true;
		}
		
		// 기존 비밀번호가 일치하지 않으면 - false 반환
		return false;
	}
	
	// 이메일 수신 여부 변경
	@RequestMapping("/editReceiveMail.do")
	@ResponseBody
	public void editReceiveMail(@RequestParam String receive_mail, HttpServletRequest request) throws Exception {
		
		// userSession 불러오기
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userSession");
		
		// 이메일 수신 여부 변경(receive_mail값을 DB에 그대로 입력)
		editMyInfoService.editReceiveMail(receive_mail, userVO.getUser_idx());
		
		// userSession 삭제
		session.removeAttribute("userSession");
		
		// 변경된 userSession 생성
		userVO.setReceive_mail(receive_mail);
		session.setAttribute("userSession", userVO);
	}
}
