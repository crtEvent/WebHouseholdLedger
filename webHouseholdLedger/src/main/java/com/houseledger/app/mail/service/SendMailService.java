package com.houseledger.app.mail.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.houseledger.app.mail.dto.WriteMailDTO;

@Service("sendMailService")
public class SendMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public boolean sendMail(WriteMailDTO writeMailDTO) {
		
		// MimeMessage 타입 객체 생성
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setTo(writeMailDTO.getMail_to()); // 받는 사람 주소
			messageHelper.setFrom("보내는 메일 주소", "웹가계부"); // 보내는 사람
			messageHelper.setSubject(writeMailDTO.getMail_subject()); // 제목
			messageHelper.setText(writeMailDTO.getMail_content(), true); // 내용
			
			// 메일 보내기
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
