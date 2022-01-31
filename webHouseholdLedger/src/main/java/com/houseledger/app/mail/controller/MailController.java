package com.houseledger.app.mail.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.houseledger.app.mail.dto.SelectMailDTO;
import com.houseledger.app.mail.dto.WriteMailDTO;
import com.houseledger.app.mail.service.MailListService;
import com.houseledger.app.mail.service.SendMailService;
import com.houseledger.app.mail.service.StoreMailService;

@Controller
@RequestMapping("/admin/mail")
public class MailController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private StoreMailService storeMailService;
	
	@Autowired
	private MailListService mailListService;
	
	// [페이지] - 메일 쓰기 페이지
	@RequestMapping(value="/writeMail.do")
	public String mail_write() throws Exception {
		return "/admin/admin_mail_write";
	}
	
	// [페이지] - 보낸 메일함
	@RequestMapping(value="/sentMailBox.do")
	public String  sent_mail_box(Model model, SelectMailDTO dto) throws Exception {
		
		model.addAttribute("pagingDTO", mailListService.getPaging(dto));
		model.addAttribute("sentMailList", mailListService.getMailList(dto));
		
		return "/admin/admin_mail_sent_list";
	}
	
	// [페이지] - 저장된 메일 양식 목록 페이지
	@RequestMapping(value="/savedMailFormList.do")
	public String  mail_form_list() throws Exception {
		return "";
	}
	
	// [페이지] - 메세지 페이지
	@RequestMapping(value="/mailMessage.do")
	public String  mail_message(Model model, @RequestParam("mail_message") String mail_message) throws Exception {
		
		model.addAttribute("mail_message", mail_message);
		
		return "/admin/admin_mail_message";
	}
	
	// [기능] - 메일 보내기
	@PostMapping(value="/sendMail.do")
	@ResponseBody
	public boolean sendMail(RedirectAttributes redirect, WriteMailDTO writeMailDTO) throws Exception {
		
		// 메일 보내기
		if(sendMailService.sendMail(writeMailDTO)) {
			// 메일 보내기 성공일 경우 
			// 메일 DB에 저장
			storeMailService.storeMailToDB(writeMailDTO);
			return true;
		};
		return false;
	}
	
	// [기능] - 메일 양식 저장
	@RequestMapping(value="/saveMailForm.do")
	public String  save_mail_form() throws Exception {
		return "";
	}
	
	// [기능] - 저장된 메일 양식 불러오기
	@RequestMapping(value="/loadMailForm.do")
	public String  load_mail_form() throws Exception {
		return "";
	}
	
	// [기능] - 저장된 메일 양식 삭제
	@RequestMapping(value="/deleteMail.do")
	public String  delete_mail_form(int[] checkedMail) throws Exception {
		
		mailListService.deleteMailList(checkedMail);
		
		return "redirect:/admin/mail/sentMailBox.do?currentpage=1";
	}
}