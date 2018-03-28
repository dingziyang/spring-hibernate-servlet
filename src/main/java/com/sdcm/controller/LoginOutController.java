package com.sdcm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcm.common.BaseController;

/**
 * 退出登录controller
 * @author XUSANDUO
 *
 */
@WebServlet("/loginOut.do")
public class LoginOutController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//注销session
		req.getSession().invalidate();
		//重定向到登录
		res.sendRedirect(req.getContextPath() + "/toLogin.do");
		
	}
	
	
}
