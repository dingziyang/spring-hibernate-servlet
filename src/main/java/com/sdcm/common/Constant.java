package com.sdcm.common;

import java.math.BigDecimal;

/**
 * 常量类
 * @author XUSANDUO
 *
 */
public class Constant {
	
	/**存入session中的用户信息名称*/
	public static final String USER_SESSION = "userSession";
	
	/**默认每页显示条数*/
	public static final int PAGE_SIZE = 5;
	
	/**必须要老板审核的金额的临界值*/
	public static final BigDecimal NEED_BOSS_PASS = new BigDecimal(3000);
	
	/**上传文件存储目录*/
    public static final String UPLOAD_DIRECTORY = "upload";
    
    /***yyyy-MM-dd HH:mm:ss格式时间字符串*/
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
