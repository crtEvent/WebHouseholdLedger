package com.houseledger.app.user.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public class SignUpVO {
	
	@NotBlank(message="아이디를 입력해 주세요.")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z]).{5,20}$", message="ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.")
	@Length(min=5, max=20, message="ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.")
	private String user_id;
	
	@NotBlank(message="이메일을 입력해 주세요.")
	@Email(message="Email형식이 올바르지 않습니다.")
	private String user_email;
	
	@NotBlank(message="비밀번호를 입력해 주세요.")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{5,25}$", message="Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.")
	@Length(min=5, max=25, message="Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.")
	private String user_password;
	
	@NotBlank(message="비밀번호를 다시 입력해 주세요.")
	private String user_password_check;
	
	private String receive_mail;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_password_check() {
		return user_password_check;
	}

	public void setUser_password_check(String user_password_check) {
		this.user_password_check = user_password_check;
	}

	public String getReceive_mail() {
		return receive_mail;
	}

	public void setReceive_mail(String receive_mail) {
		this.receive_mail = receive_mail;
	}
}
