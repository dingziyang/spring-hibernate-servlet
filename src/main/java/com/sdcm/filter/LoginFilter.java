package com.sdcm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdcm.common.Constant;

/**
 * 登录检查过滤器：
 *	在所有的(不包括登录相关的请求)请求前，
 *	判断用户是否已登录，若已登录则请求继续，
 *	否则拒绝用户的请求，重定向到登录页。
 */
public class LoginFilter implements Filter {
	
	private String[] paths;

	public void destroy() {
		System.out.println("====LoginFilter destroy====");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		//Tomcat传入的参数是HTTP相关参数，就是当前参数的子类型，可以强转。
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//排除掉不需要校验的请求
//		String[] paths = new String[]{"/toLogin.do","/createImg.do","/login.do"};
		for(String path : paths) {
			if(path.equals(request.getServletPath())) {
				//如果当前路径等于要排除的路径，
				//则当前路径不需要校验了，放行
				chain.doFilter(req, res);
				return;
			}
		}
		
		String url = request.getRequestURI();
		if(url.endsWith(".css")||url.endsWith(".js")||url.endsWith(".jpg")
                ||url.endsWith(".gif")||url.endsWith(".png")){
			chain.doFilter(req, res);
		}
		
		//从session中获取userSession，以此来判断用户是否已登录。
		HttpSession session = request.getSession();
		Object userSession = session.getAttribute(Constant.USER_SESSION);
		if(userSession == null) {
			//未登录，重定向到登录页
			response.sendRedirect(request.getContextPath() + "/toLogin.do");
		} else {
			//已登录，请求继续
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		String ignorePath = cfg.getInitParameter("ignorePath"); // InitParameter在web.xml中配置的
		paths = ignorePath.split(",");  // 逗号在web.xml中配置的
	}

}





