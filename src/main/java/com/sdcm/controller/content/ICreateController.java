package com.sdcm.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.sdcm.common.BaseController;

/**
 * 由我发起的controller
 * @author XUSANDUO
 *
 */
@WebServlet("/icreate.do")
public class ICreateController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/content/icreate.jsp").forward(req, res);
		
	}
	
	
}
