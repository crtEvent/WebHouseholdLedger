package com.houseledger.app.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PerformanceFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 전처리 작업
		long startTime = System.currentTimeMillis();
		
		// 2. 서블릿 또는 다음 필터를 호출
		chain.doFilter(request, response); 
		
		// 3. 후처리 작업
		System.out.print("PerformanceFilter - ["+((HttpServletRequest)request).getRequestURI()+"]");
		System.out.println(" 소요시간: "+(System.currentTimeMillis()-startTime)+"ms\n");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
