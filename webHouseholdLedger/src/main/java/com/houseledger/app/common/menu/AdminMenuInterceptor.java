package com.houseledger.app.common.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminMenuInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String servletPath = request.getServletPath();
		String menu = "admin";
		String subMenu = "";
		
		switch(servletPath) {
		case "/admin/userManagement.do":
			subMenu = "user";
			break;
		case "이메일 보내기":
			subMenu = "email";
			break;
		default:
			break;
		}
		System.out.println("servletPath: "+servletPath+", menu: "+menu+", subMenu: "+subMenu);
		modelAndView.addObject("menu", menu);
		modelAndView.addObject("subMenu", subMenu);
		
	}
	
}