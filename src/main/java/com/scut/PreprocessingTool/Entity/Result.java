package com.scut.PreprocessingTool.Entity;

import com.scut.PreprocessingTool.enums.ResultEnum;

public class Result<T> {

	private int code;          //前端返回结果编码
	
	private String msg;        //结果信息
	
	private T data;            //返回数据

	public Result(){
		this(ResultEnum.SUCCESS);
	}
	
	public Result(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(ResultEnum resultEnum){
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}
	
	public Result(ResultEnum resultEnum, T data){
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
