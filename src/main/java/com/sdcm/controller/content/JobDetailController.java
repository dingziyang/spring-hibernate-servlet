package com.sdcm.controller.content;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






















import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sdcm.common.BaseController;
import com.sdcm.common.Constant;
import com.sdcm.po.JobInfo;
import com.sdcm.po.JobProcess;
import com.sdcm.po.JobType;
import com.sdcm.po.UserInfo;
import com.sdcm.service.JobInfoService;
import com.sdcm.service.JobProcessService;
import com.sdcm.service.JobTypeService;
import com.sdcm.service.UserInfoService;
import com.sdcm.utils.Util;
import com.sdcm.vo.JobInfoVo;
import com.sdcm.vo.JobProcessVo;

/**
 * 工作流详情controller
 * @author XUSANDUO
 *
 */
@WebServlet("/jobDetail.do")
public class JobDetailController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(JobDetailController.class);
	
	private JobInfoService jobInfoService;
	private JobProcessService jobProcessService;
	private UserInfoService userInfoService;
	private JobTypeService jobTypeService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		jobInfoService = lookUp("jobInfoService");
		jobProcessService = lookUp("jobProcessService");
		userInfoService = lookUp("userInfoService");
		jobTypeService = lookUp("jobTypeService");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			String jobId = req.getParameter("id");
			if(jobId != null ){
				JobInfo job = this.jobInfoService.get(Long.parseLong(jobId));
				UserInfo us = this.userInfoService.get(job.getCreateBy());
				JobInfoVo jv = new JobInfoVo();
				BeanUtils.copyProperties(jv, job);
				jv.setCreateName(us.getUserNickName());
				jv.setCreateTimeStr(Util.formatDate(job.getCreateTime(), Constant.TIME_FORMAT));
				
				// 加工附件地址
				if(!StringUtils.isEmpty(jv.getJobAnnexUrl())){
					int idx = jv.getJobAnnexUrl().lastIndexOf("/") +1;
					if(idx != -1){
						jv.setAnnexName(jv.getJobAnnexUrl().substring(idx, jv.getJobAnnexUrl().length()));
					}else{
						jv.setAnnexName("");
					}
				}else{
					jv.setAnnexName("");
				}
				
				// 工作类型名
				JobType jt = new JobType();
				jt.setTypeNo(job.getJobTypeNo());
				List<JobType> jtList = this.jobTypeService.getList(jt);
				if(!CollectionUtils.isEmpty(jtList)){
					jv.setJobTypeName(jtList.get(0).getTypeName());
				}else{
					log.warn("工作类型编码为["+jv.getJobTypeNo()+"]的记录在JOB_TYPE表中不存在");
					jv.setJobTypeName("未知类型");
				}
				
				JobProcess process = new JobProcess();
				process.setJobId(job.getId());
				List<JobProcessVo> pList = this.jobProcessService.getVoList(process);
				Collections.reverse(pList);
				req.setAttribute("job", jv);
				req.setAttribute("pList", pList);
			}else{
				log.warn("传入的jobid不合法");
			}
			req.getRequestDispatcher("WEB-INF/content/jobDetail.jsp").forward(req, res);
		} catch (NumberFormatException e) {
			log.error("加载详情失败");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("加载详情失败");
			e.printStackTrace();
		}
			
		
		
	}
	
	
}
