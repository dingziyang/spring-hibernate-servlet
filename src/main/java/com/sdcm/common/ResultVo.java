package com.sdcm.common;

import java.io.Serializable;

/**
 * 通用的返回VO
 * @author XUSANDUO
 *
 * @param <T>
 */
public class ResultVo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int status;//状态
	private String message;//信息
	private T data;//数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
