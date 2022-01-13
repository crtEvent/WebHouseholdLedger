package com.houseledger.app.common.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class StatsMenuInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String servletPath = request.getServletPath();
		String menu = "stats";
		String subMenu = "";
		
		switch(servletPath) {
		case "/stats/category.do":
			subMenu = "category";
			break;
		case "stats/yearly.do":
			subMenu = "yearly";
			break;
		default:
			break;
		}
		
		modelAndView.addObject("menu", menu);
		modelAndView.addObject("subMenu", subMenu);
		
	}
	
}
