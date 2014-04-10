package com.hejian.men.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * 
 * @author jason
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController {
	
	@SuppressWarnings("finally")
	@RequestMapping(method = RequestMethod.GET)
	public String logout() {
		try{
			HttpRequestSessionUtils.clearSession();
		}catch(Exception e){
			
		} finally{
			return "account/login";
		}
	}
	
}
