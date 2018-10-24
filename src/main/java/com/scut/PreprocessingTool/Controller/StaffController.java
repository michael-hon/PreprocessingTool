package com.scut.PreprocessingTool.Controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scut.PreprocessingTool.Entity.*;
import com.scut.PreprocessingTool.Service.StaffService;
import com.scut.PreprocessingTool.enums.ResultEnum;



@RestController
public class StaffController {
	
	@Resource
	private StaffService staffService;
	/*
	 * 登录
	 * Arguments:
	 * username -- 用户名
	 * password -- 密码
	 * 
	 * Return:
	 * result -- 登录结果
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Result Login(@RequestParam(value="username", required=true)String username, 
			            @RequestParam(value="password", required=true)String password,
			            HttpSession session){
		/*
		 *逻辑代码 
		 */
		
		Staff staff = staffService.doLogin(username, password);
		if(staff == null){
			System.out.println("=================");
			return new Result(ResultEnum.LOGIN_FAIL);
		}
		session.setAttribute("staff", staff);   //用户保存到session
		return new Result(ResultEnum.SUCCESS, staff);
	}
	
	/*
	 * 注册
	 * 
	 * Arguments:
	 * username --    用户名
	 * password --    密码
	 * telephone --   电话号码
	 * 
	 * Returns:
	 * result   --    注册结果
	 * 
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public Result register(@RequestParam(value="username", required=true)String username, 
			               @RequestParam(value="password", required=true)String password, 
			               @RequestParam(value="telephone", required=true)String telephone){
		
		/*
		 * 逻辑代码
		 */
		String registerResult = staffService.doRegister(username, password, telephone);
		//注册成功
		if(registerResult.equals("SUCCESS")){
			return new Result(ResultEnum.SUCCESS);
		}
		return new Result(ResultEnum.REGISTER_FAIL);
	}
	
	/*
	 * 判断用户是否登录
	 * 
	 * 
	 * Returns:
	 * result  --    判断结果
	 */
	@RequestMapping(value="/getLoginInfo", method=RequestMethod.POST)
	public Result isLogin(HttpSession session){
		
		/*
		 * 逻辑代码
		 */
		Staff staff = (Staff) session.getAttribute("staff");
		String name = staff==null?"":staff.getName();
		Result result = new Result(ResultEnum.SUCCESS, name);
		return result;
	}
	
	/*
	 * 注销
	 */
	@RequestMapping(value="/logout")
	public Result logout(HttpSession session){
		
		/*
		 * 逻辑代码
		 */
		session.setAttribute("staff", null);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
