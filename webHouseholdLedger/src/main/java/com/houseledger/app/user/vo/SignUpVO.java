package com.houseledger.app.user.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public class SignUpVO {
	
	private String user_idx;
	
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
	private String user_image = "data:image/png;base64,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";
	
	
	public String getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}

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

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	
}
