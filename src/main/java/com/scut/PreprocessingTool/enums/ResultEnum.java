package com.scut.PreprocessingTool.enums;

public enum ResultEnum {
    SUCCESS(0,"SUCCESS"),
    PARAM_ERROR(101,"参数错误"),
    FILE_FORMAT_ERROR(102,"文件格式有误!"),
    FAIL(400,"失败"),
    UNAUTHORIZED(401,"未授权"),
    LOGIN_FAIL(402,"登录失败"),
    SERVER_ERROR(500,"服务器错误"),
	REGISTER_FAIL(600,"注册失败");
	
	
	private int code;
	private String msg;
	
	ResultEnum(int code, String msg) {
		// TODO Auto-generated constructor stub
		this.setCode(code);
		this.setMsg(msg);
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
	
	
}
