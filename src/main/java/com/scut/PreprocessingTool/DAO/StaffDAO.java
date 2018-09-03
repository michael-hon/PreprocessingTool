package com.scut.PreprocessingTool.DAO;

import com.scut.PreprocessingTool.Entity.*;

//用户表增删改查
public interface StaffDAO {
	//查询用户
	public Staff findStaff(String name, String password);
	
	//插入用户
	public void insertStaff(Staff staff);
}
