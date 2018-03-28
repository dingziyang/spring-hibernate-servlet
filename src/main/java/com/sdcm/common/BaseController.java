package com.sdcm.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 所有控制器的基类
 * @author XUSANDUO
 *
 */
public class BaseController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private Class<?> method_types[] = { HttpServletRequest.class, HttpServletResponse.class };

	public BaseController() {
		super();
	}
	
	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String methodName = request.getParameter("method");//页面提交过来的参数

		if (StringUtils.isEmpty(methodName)) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					request.getRequestURL() + ",语法时，没有提供method参数");
		}

		try {
			// 通过反射机制调用子类的相应方法
			Method method = this.getClass().getMethod(methodName, method_types);
			Object args[] = { request, response };
			method.invoke(this, args);// 调用子类方法
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,request.getRequestURL() + ",未找到方法：" + methodName);
			e.printStackTrace();
		}
	}
	
	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 获取业务逻辑层对象
	 * @param serviceName//存的是"adminRolesService"
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T lookUp(String serviceName){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());  
        return (T) wac.getBean(serviceName);
	}
	
}
