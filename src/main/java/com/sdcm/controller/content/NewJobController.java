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
import com.sdcm.service.JobTypeService;
import com.sdcm.vo.JobTypeVo;

/**
 * 创建工作流controller
 * @author XUSANDUO
 *
 */
@WebServlet("/newjob.do")
public class NewJobController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(NewJobController.class);
	
	private JobTypeService jobTypeService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		jobTypeService = lookUp("jobTypeService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			List<JobTypeVo> jobTypeVo = this.jobTypeService.getVoList();
			req.setAttribute("jobTypeVoList", jobTypeVo);
			
		} catch (Exception e) {
			log.error("创建工作流时，查询jobTypeVoList,异常！");
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("WEB-INF/content/newjob.jsp").forward(req, res);
	}
	
	
}
