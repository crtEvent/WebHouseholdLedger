package com.houseledger.app.common.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LedgerMenuInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String servletPath = request.getServletPath();
		String menu = "";
		
		switch(servletPath) {
		case "/ledger/details.do":
			menu = "ledger";
			break;
		case "/ledger/calendar.do":
			menu = "calendar";
			break;
		case "/ledger/editMyAsset.do":
			menu = "asset";
			break;
		default:
			break;
		}
		
		modelAndView.addObject("menu", menu);
		
	}
	
}
