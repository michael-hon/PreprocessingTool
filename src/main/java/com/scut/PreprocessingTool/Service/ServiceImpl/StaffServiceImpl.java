package com.scut.PreprocessingTool.Service.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scut.PreprocessingTool.DAO.StaffDAO;
import com.scut.PreprocessingTool.Entity.Staff;
import com.scut.PreprocessingTool.Service.StaffService;

@Service("staffService")
public class StaffServiceImpl implements StaffService{

	@Resource
	private StaffDAO staffDAO;
	
	@Override
	public Staff doLogin(String name, String password) {
		// TODO Auto-generated method stub
		Staff staff = staffDAO.findStaff(name, password);
		return staff;
	}

	@Override
	public String doRegister(String name, String password, String telephone) {
		// TODO Auto-generated method stub
		try{
			Staff staff = new Staff();
			staff.setName(name);
			staff.setPassword(password);
			staff.setTelephone(telephone);
			staffDAO.insertStaff(staff);
		}
		catch (Exception e) {
			// TODO: handle exception
			return "FAIL";
		}
		return "SUCCESS";
	}

}
