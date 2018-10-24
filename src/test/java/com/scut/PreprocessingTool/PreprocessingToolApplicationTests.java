package com.scut.PreprocessingTool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.scut.PreprocessingTool.DAO.StaffDAO;
import com.scut.PreprocessingTool.Entity.Staff;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PreprocessingToolApplicationTests {
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Test
	public void testInsert(){
		Staff staff = new Staff();
		staff.setName("Hong Yin");
		staff.setPassword("123456");
		staff.setTelephone("18826074677");
		staffDAO.insertStaff(staff);
		Staff staff1 = new Staff();
		staff1.setName("Michael");
		staff1.setPassword("1234567890");
		staff1.setTelephone("18218734119");
		staffDAO.insertStaff(staff1);
	}
	
	@Test
	public void testLogin(){
		Staff staff = staffDAO.findStaff("HongYin", "123456");
		if(staff==null){
			System.out.println("=========不存在");
		}
		else {
			System.out.println(staff.getName());
		}
	}
	


}
