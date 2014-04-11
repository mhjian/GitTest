package com.hejian.men.web.account;

import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.hejian.men.entity.User;
import com.hejian.men.service.account.AccountService;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired  
	private HttpSession session;  
	
	protected AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		try{
			HttpRequestSessionUtils.clearSession();
		}catch(Exception e){
			
		}
		return "account/login";
	}

	/*@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "account/login";
	}*/
	
	/**
	 * 登录验证
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String validLogin(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, 
							 @RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
							 Model model) {
		User user = accountService.findUserByLoginName(userName);
		if (user != null) {


            byte[] salt = Encodes.decodeHex(user.getSalt());

    		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);//登录时输入的密码
    		String loginPwd = new String(hashPassword);
            
            byte[] pwdB =  Encodes.decodeHex(user.getPassword());//数据库中获取摘要
           	String passwd = new String(pwdB);
           	session = HttpRequestSessionUtils.getHttpSesson();
           	if(loginPwd.equals(passwd)){
           		session.setAttribute("userId", user.getId());
    			return "redirect:/task/";
           	}else{
           		return "account/login";
           	}
		} else {
			return "account/login";
		}
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
