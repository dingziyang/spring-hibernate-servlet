package com.sdcm.controller.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.service.JobInfoService;
import com.sdcm.vo.JobInfoVo;
import com.sdcm.vo.UserVo;

/**
 * 提交创建的工作流controller
 * @author XUSANDUO
 *
 */
@WebServlet("/commitjob.do")
public class CommitJobController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(CommitJobController.class);
	
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
			String jobTitle = req.getParameter("job_title");
			String jobContent = req.getParameter("job_content");
			String money = req.getParameter("money");
			String jobTypeNo = req.getParameter("job_type");
			String jobAnnexUrl = req.getParameter("job_annex_url");
			String job_next = req.getParameter("job_next");
			
			// 新建一条工作流
			JobInfoVo vo = new JobInfoVo();
			vo.setCreateBy(user.getId());
			vo.setUpdateBy(user.getId());
			vo.setJobAnnexUrl(jobAnnexUrl);
			vo.setJobContent(jobContent);
			vo.setJobTitle(jobTitle);
			vo.setJobTypeNo(jobTypeNo);
			vo.setMoney(new BigDecimal(money));
			vo.setTargetUserId(Long.parseLong(job_next));
		
			jobInfoService.commitJob(vo);
			
			writer.print("保存成功，success");
		} catch (Exception e) {
			log.error("保存工作流失败");
			writer.print("保存失败，error");
			e.printStackTrace();
		}
	}
	
	
}
