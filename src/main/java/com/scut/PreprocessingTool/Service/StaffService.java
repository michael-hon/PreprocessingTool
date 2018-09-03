package com.scut.PreprocessingTool.Service;

import com.scut.PreprocessingTool.Entity.Staff;

/*
 * 登录注册模块逻辑层接口
 * 
 */
public interface StaffService {
	
	//登录
	public Staff doLogin(String name, String password);
	
	//注册
	public String doRegister(String name, String password, String telephone);
	
	
}
