package com.houseledger.app.common.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminMailMenuInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String servletPath = request.getServletPath();
		String menu = "admin";
		String subMenu = "email";
		
		System.out.println("servletPath: "+servletPath+", menu: "+menu+", subMenu: "+subMenu);
		modelAndView.addObject("menu", "admin");
		modelAndView.addObject("subMenu", "email");
		
	}
}
