package com.sdcm.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.service.JobProcessService;
import com.sdcm.vo.UserVo;

/**
 * 处理工作流的controller
 * @author XUSANDUO
 *
 */
@WebServlet("/handleJob.do")
public class HandleJobController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(HandleJobController.class);
	
	private JobProcessService jobProcessService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		jobProcessService = lookUp("jobProcessService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			UserVo user = (UserVo)req.getSession().getAttribute(Constant.USER_SESSION);
			String typeValue = req.getParameter("typeValue");
			String jobId = req.getParameter("jobId");
			Long jid = Long.parseLong(jobId);
			this.jobProcessService.handleJob(user, jid, typeValue);
			res.getWriter().print("操作成功！");
		} catch (NumberFormatException e) {
			log.error("传入后台的typeValue或jobId不合法");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("处理工作流失败！");
			e.printStackTrace();
		}
		
	}
	
	
}
