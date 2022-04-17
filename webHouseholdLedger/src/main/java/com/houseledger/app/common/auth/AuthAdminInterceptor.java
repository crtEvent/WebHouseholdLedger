package com.houseledger.app.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.houseledger.app.user.vo.UserVO;

public class AuthAdminInterceptor extends HandlerInterceptorAdapter {
	
	/* admin이 아니면 로그인 페이지로 이동 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		
		if(userSession.getUser_idx().equals("1")) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath()+"/user/signin.do");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
