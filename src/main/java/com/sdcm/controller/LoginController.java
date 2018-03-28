package com.sdcm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.service.UserInfoService;
import com.sdcm.vo.UserVo;

/**
 * 登录controller
 * @author XUSANDUO
 *
 */
@WebServlet("/login.do")
public class LoginController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	//private Logger log = Logger.getLogger(this.getClass());
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private UserInfoService userInfoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userInfoService = lookUp("userInfoService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			System.out.println(userInfoService);
			UserVo user = userInfoService.getUserByUserName(username);
			if(user != null && user.getUserPwd().equals(password)){
				req.getSession().setAttribute(Constant.USER_SESSION, user);
				res.sendRedirect(req.getContextPath() + "/toHome.do");
				
			}else{
				req.setAttribute("msg", "用户名或密码错误");
				req.getRequestDispatcher("WEB-INF/toLogin.jsp").forward(req, res);
			}
		} catch (Exception e) {
			res.sendRedirect("error.jsp");
			e.printStackTrace();
			log.error("登录异常");
		}
		
	}
	
	
}
