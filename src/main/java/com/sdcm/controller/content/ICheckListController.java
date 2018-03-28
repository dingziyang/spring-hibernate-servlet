package com.sdcm.controller.content;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.po.JobInfo;
import com.sdcm.po.JobProcess;
import com.sdcm.service.JobInfoService;
import com.sdcm.service.JobProcessService;
import com.sdcm.utils.JsonUtils;
import com.sdcm.utils.Page;
import com.sdcm.vo.JobInfoVo;
import com.sdcm.vo.UserVo;

/**
 * 要我审核的的列表controller
 * @author XUSANDUO
 *
 */
@WebServlet("/icheckList.do")
public class ICheckListController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(ICheckListController.class);
	
	private JobInfoService jobInfoService;
	private JobProcessService jobProcessService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		jobInfoService = lookUp("jobInfoService");
		jobProcessService = lookUp("jobProcessService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			UserVo user = (UserVo)req.getSession().getAttribute(Constant.USER_SESSION);
			JobInfo obj = new JobInfo();
			
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String page = req.getParameter("page");
			String limit = req.getParameter("limit");
			
			if(!StringUtils.isEmpty(id)){
				obj.setId(Long.parseLong(id));
			}
			if(!StringUtils.isEmpty(title)){
				obj.setJobTitle(title);
			}
			
			JobProcess proObj = new JobProcess();
			proObj.setNext(user.getId());
			List<JobProcess> pList = this.jobProcessService.getList(proObj);
			Set<Long> jobIds = new HashSet<>();
			pList.forEach(p -> {
				jobIds.add(p.getJobId());
			});
			
			Page<JobInfoVo> data = this.jobInfoService.getICheckList(
					obj, Integer.parseInt(page), Integer.parseInt(limit),jobIds,user.getId(),user.getRoleLevel());
			res.getWriter().write(JsonUtils.objectToJson(data));
		} catch (NumberFormatException e) {
			log.error("传入后台的page和limit不是数字");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("获取我审核列表失败！");
			e.printStackTrace();
		}
		
	}
	
	
}
