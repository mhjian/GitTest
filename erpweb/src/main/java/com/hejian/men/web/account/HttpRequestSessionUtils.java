package com.hejian.men.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestSessionUtils {
	
	public static HttpSession getHttpSesson(){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		
		return request.getSession();
	}
	
	
	/**
	 * 当前登录用户Id.
	 */
	public  static String getCurrentUserId() {
		return getHttpSesson().getAttribute("userId").toString();
	}
	
	/**
	 * 当前登录用户Id.
	 */
	public  static void clearSession() {
		getHttpSesson().removeAttribute(getCurrentUserId());
	}
	
}
