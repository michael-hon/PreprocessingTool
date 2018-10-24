package com.scut.PreprocessingTool.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.scut.PreprocessingTool.Entity.*;

//用户表增删改查
@Mapper
public interface StaffDAO {
	//查询用户
	public Staff findStaff(@Param("name")String name, 
			               @Param("password")String password);
	
	//插入用户
	public void insertStaff(Staff staff);
}
