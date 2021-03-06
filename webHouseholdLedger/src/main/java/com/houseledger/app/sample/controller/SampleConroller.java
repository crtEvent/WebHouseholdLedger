package com.houseledger.app.sample.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.houseledger.app.sample.service.SampleServiceImpl;
import com.houseledger.app.user.vo.SignInVO;
import com.houseledger.app.user.vo.UserVO;



@Controller
public class SampleConroller {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required=true)
	DriverManagerDataSource dataSource;
	
	@Resource(name="sampleService")
    private SampleServiceImpl sampleService;
	
	// LoggerInterceptor 테스트
	@RequestMapping(value="/sample/testLogger.do")
	public String test_logger(Model model) throws Exception{
		
		log.debug("LoggerInterceptor 실행");
		
		return "";
	}
	
	// MySQL DB 연결 확인
	@RequestMapping(value="/sample/testDB.do")
	public String testDB(Model model) {
		
		log.debug("MySQL 연결 테스트");
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NOW() AS NOW;");
				while(rs.next()) {
				model.addAttribute("servertime",rs.getString("now"));
				log.debug("servertime: "+rs.getString("now"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close(); 
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try { 
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	
	// MyBatis 테스트
	@RequestMapping(value="/sample/testMyBatis.do")
	public String test_mybatis(Model model) throws Exception{
		
		log.debug("MyBatis 연결 테스트");
		log.debug("getTime: "+sampleService.getTime());
		
		
		return "";
	}
	
	// 자동 로그인
	@RequestMapping(value="/sample/auto_signin.do")
	public String auto_sign_in(Model model, RedirectAttributes redirectAttributes) throws Exception{
		
		SignInVO signInVO = new SignInVO();
		signInVO.setUser_id("admin");
		signInVO.setUser_password("qwe123!");
		
		redirectAttributes.addFlashAttribute("signInVO", signInVO);
		
		return "redirect:/user/excute_signin.do";
	}
	
	// 경로 찾기
	@RequestMapping(value="/sample/find_root.do")
	public String find_root(Model model, HttpServletRequest request) throws Exception{
		
		log.debug("request.getSession().getServletContext().getRealPath() : ");
		log.debug(request.getSession().getServletContext().getRealPath(""));
		
		return "";
	}
	
	@RequestMapping(value="/sample/error500.do")
	public String serverError() throws Exception {
		
		throw new Exception();
	}

}
