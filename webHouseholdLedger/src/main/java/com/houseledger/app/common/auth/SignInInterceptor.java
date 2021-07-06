package com.houseledger.app.common.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.houseledger.app.user.vo.SignInVO;
import com.houseledger.app.user.vo.UserVO;

public class SignInInterceptor extends HandlerInterceptorAdapter{
	
	protected Logger log = LoggerFactory.getLogger(SignInInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		ModelMap modelMap = modelAndView.getModelMap();
		UserVO userVO = (UserVO) modelMap.get("signIn");
		
		if(userVO == null) { // 로그인 실패
			log.debug("로그인 실패");
			request.setAttribute("signInError", "ID 또는 Password가 일치하지 않습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/user/signin.do");
			rd.forward(request, response);
			return;
		}
		
		// session 생성
		HttpSession session = request.getSession();
		session.setAttribute("userSession", userVO);
		
		// 아이디 저장
		SignInVO signInVO = (SignInVO) modelMap.get("signInVO");
		if(signInVO.getRemember_user() == null) {
			// cookie 삭제
			Cookie cookie = new Cookie("remember_user", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			log.debug("cookie 삭제");
		}else {
			// cookie 저장
			Cookie cookie = new Cookie("remember_user", signInVO.getUser_id());
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			log.debug("cookie 저장");
		}
		
		log.debug("로그인 성공, user_id: "+userVO.getUser_id());
		response.sendRedirect(request.getContextPath()+"/ledger/details.do");
	}
	
	

}
