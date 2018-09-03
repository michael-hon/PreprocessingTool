package com.scut.PreprocessingTool.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.scut.PreprocessingTool.Entity.Result;

public class SourceFileAnalysisController {
	
	
	/*
	 * 上传文件(支持批量上传)
	 * 
	 * Arguments:
	 * multiReq   --   请求
	 * session    --   连接
	 * 
	 * 
	 * Returns:
	 * result     --   上传结果
	 * 
	 * 
	 * 
	 * (注意：参数可能会有变化)
	 */
	
	public Result uploadFile(MultipartHttpServletRequest multiReq, HttpSession session){
		
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		return result;
	}
	
	
	/*
	 * 文件解析（文件格式：pdf或word)
	 * 
	 * 
	 * Arguments:
	 * file_id    --   文件id
	 * 
	 * Returns:
	 * result     --   解析结果
	 * 
	 */
	public Result AnalysisSingleText(int file_id){
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		
		return result;
		
	}
	
	/*
	 * 批量文件解析(文件格式:pdf文件或word文件)
	 * 
	 * Arguments:
	 * file_id    --    文件id
	 * 
	 * Returns:
	 * result     --    解析结果
	 * 
	 */
	public Result AnalysisBatchText(List<Integer>file_id){
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		return result;
	}
	
	
}
