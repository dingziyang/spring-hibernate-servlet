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
import com.sdcm.po.UserInfo;
import com.sdcm.service.UserInfoService;
import com.sdcm.vo.UserVo;

/**
 * 首页controller
 * @author XUSANDUO
 *
 */
@WebServlet("/toHome.do")
public class HomeController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	//private Logger log = Logger.getLogger(this.getClass());
	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	
	private UserInfoService userInfoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userInfoService = lookUp("userInfoService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		UserVo user = (UserVo)req.getSession().getAttribute(Constant.USER_SESSION);
		log.info(user.toString());
		req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, res);
	}
	
	
}
