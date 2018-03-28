package com.sdcm.controller.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.BaseController;
import com.sdcm.po.JobInfo;
import com.sdcm.service.JobInfoService;

/**
 * 下载附件controller
 * @author XUSANDUO
 *
 */
@WebServlet("/download.do")
public class DownloadController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(DownloadController.class);

	private JobInfoService jobInfoService;

	@Override
	public void init() throws ServletException {
		super.init();
		jobInfoService = lookUp("jobInfoService");
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JobInfo job;
		try {
			String jobid = request.getParameter("jobId");
			job = this.jobInfoService.get(Long.parseLong(jobid));
			File f = new File(job.getJobAnnexUrl());
			if (f.exists()) {
				System.out.println("存在");
				FileInputStream fis = new FileInputStream(f);
				String filename = URLEncoder.encode(f.getName(), "utf-8"); // 解决中文文件名下载后乱码的问题
				byte[] b = new byte[fis.available()];
				fis.read(b);
				response.setCharacterEncoding("utf-8");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + filename + "");
				// 获取响应报文输出流对象
				ServletOutputStream out = response.getOutputStream();
				// 输出
				out.write(b);
				out.flush();
			}else{
				System.out.println("不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
