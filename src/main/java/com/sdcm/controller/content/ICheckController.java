package com.sdcm.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.service.ProcessTypeService;
import com.sdcm.vo.ProcessTypeVo;

/**
 * 要我审批的controller
 * @author XUSANDUO
 *
 */
@WebServlet("/icheck.do")
public class ICheckController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(ICheckController.class);
	
	private ProcessTypeService processTypeService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		processTypeService = lookUp("processTypeService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			List<ProcessTypeVo> processTypeVoList = this.processTypeService.getVoList();
			req.setAttribute("processTypeVoList", processTypeVoList);
		} catch (Exception e) {
			log.error("查询processTypeVoList,异常！");
			e.printStackTrace();
		}
		req.getRequestDispatcher("WEB-INF/content/icheck.jsp").forward(req, res);
		
	}
	
	
}
