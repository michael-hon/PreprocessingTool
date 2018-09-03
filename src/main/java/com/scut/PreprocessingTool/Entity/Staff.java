package com.scut.PreprocessingTool.Entity;

public class Staff {
	
	private int staff_id;         //用户id,数据库主键
	
	private String name;          //用户名
	
	private String password;      //密码
	
	private String telephone;     //电话号码

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
