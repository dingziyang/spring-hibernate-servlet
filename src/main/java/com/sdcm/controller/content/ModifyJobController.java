package com.sdcm.controller.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.po.JobInfo;
import com.sdcm.service.JobInfoService;
import com.sdcm.vo.UserVo;

/**
 * 修改工作流controller
 * @author XUSANDUO
 *
 */
@WebServlet("/modifyJob.do")
public class ModifyJobController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(ModifyJobController.class);
	
	private JobInfoService jobInfoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		jobInfoService = lookUp("jobInfoService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter writer = res.getWriter();
		try {
			UserVo user = (UserVo)req.getSession().getAttribute(Constant.USER_SESSION);
			String jobId = req.getParameter("jid");
			String jobTitle = req.getParameter("title");
			String jobContent = req.getParameter("content");
			
			JobInfo job = this.jobInfoService.get(Long.parseLong(jobId));
			job.setJobTitle(jobTitle);
			job.setJobContent(jobContent);
			job.setUpdateBy(user.getId());
			job.setUpdateTime(new Date());
			this.jobInfoService.update(job);
			
			writer.print("修改成功，success");
		} catch (Exception e) {
			log.error("修改工作流失败");
			writer.print("修改失败，error");
			e.printStackTrace();
		}
	}
	
	
}
