package com.sdcm.controller.content;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.Constant;

/**
 * 上传controller
 * @author XUSANDUO
 *
 */
@WebServlet("/upload.do")
public class UploadController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(UploadController.class);
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
    public UploadController(){
        super();
    }
    
    public void service(HttpServletRequest req,
		        HttpServletResponse res) throws ServletException, IOException {
		
		   PrintWriter writer = res.getWriter();
           // 检测是否为多媒体上传
           if (!ServletFileUpload.isMultipartContent(req)) {
               // 如果不是则停止
               writer.println("Error: 表单必须包含 enctype=multipart/form-data...");
               writer.flush();
               return;
           }
           
           //记住要写这句话，上传的文件名才不会乱码！！！！！！！！！
           req.setCharacterEncoding("utf-8");
           res.setContentType("text/html;charset=utf-8");
    
           // 配置上传参数
           DiskFileItemFactory factory = new DiskFileItemFactory();
           // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
           factory.setSizeThreshold(MEMORY_THRESHOLD);
           // 设置临时存储目录
           factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
    
           ServletFileUpload upload = new ServletFileUpload(factory);
            
           // 设置最大文件上传值
           upload.setFileSizeMax(MAX_FILE_SIZE);
            
           // 设置最大请求值 (包含文件和表单数据)
           upload.setSizeMax(MAX_REQUEST_SIZE);
           
           // 中文处理
           upload.setHeaderEncoding("UTF-8"); 
    
           // 构造临时路径来存储上传的文件
           // 这个路径相对当前应用的目录
           String uploadPath = req.getContextPath() + File.separator + Constant.UPLOAD_DIRECTORY;
           
           //获取项目发布路径  下的upload文件夹
           uploadPath = req.getSession().getServletContext().getRealPath("/upload");
           
                      
            
           // 如果目录不存在则创建
           File uploadDir = new File(uploadPath);
           if (!uploadDir.exists()) {
               uploadDir.mkdir();
           }
    
           try {
               // 解析请求的内容提取文件数据
//               @SuppressWarnings("unchecked")
               List<FileItem> formItems = upload.parseRequest(req);
    
               if (formItems != null && formItems.size() > 0) {
                   // 迭代表单数据
                   for (FileItem item : formItems) {
                       // 处理不在表单中的字段
                       if (!item.isFormField()) {
                           //这里处理文件中文乱码没用。。。。。。。。。
                           System.out.println(new String(item.getName().getBytes("utf-8"),"iso-8859-1"));
                           String fileName = new File(item.getName()).getName();
                           String filePath = uploadPath + File.separator + fileName;
                           File storeFile = new File(filePath);
                           // 在控制台输出文件的上传路径
                           System.out.println(filePath);
                           // 保存文件到硬盘
                           item.write(storeFile);
                           //注意编码，不然返回前端会乱码！！！
                           writer.println(filePath+"|"+"上传成功！");
                       }
                   }
               }
               
           } catch (Exception ex) {
        	   log.error("提取文件失败");
               ex.printStackTrace();
           }
	}
	
	
}
