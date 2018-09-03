package com.scut.PreprocessingTool.Controller;

import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Result;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;

/*
 * 数据校验控制层
 */
public class ValidationController {

	/*
	 * 获取文件列表
	 * 
	 * 
	 * Returns:
	 * result   --    获取结果
	 */
	public Result GetFileList(){
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 获取PDF源文件
	 * 
	 * Arguments:
	 * file_id  --   文件id
	 * 
	 * Returns:
	 * result   --   结果
	 */
	public Result GetPDFFile(){
		
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 获取标准文件基本信息
	 * 
	 * Arguments:
	 * file_id    --    文件id
	 * 
	 * Returns:
	 * result     --    获取结果
	 */
	
	public Result GetBasicInformation(int file_id){
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 校验标准基本信息
	 * 
	 * Arguments:
	 * file_id             --    文件id
	 * structure_info      --    标准基本信息,Json字符串(已校验)
	 * 
	 * 
	 * Returns:
	 * result              --    结果
	 */
	public Result ValidateBasicInformation(int file_id, String structure_info){
		/*
		 * 逻辑代码
		 * 
		 */
		Result result = new Result();
		
		return result;
	}
	
	
	/*
	 * 获取范围内容
	 * 
	 * Arguments:
	 * file_id     --     文件id
	 * 
	 * 
	 * Returns:
	 * result      --     获取结果
	 * 
	 */
	public Result GetRange(int file_id){
		
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		return result;
	}
	
	/*
	 * 校验范围
	 * 
	 * Arguments:
	 * file_id     --    文件id
	 * range       --    范围(已校验)
	 * 
	 * Returns:
	 * result      --     结果
	 * 
	 */
	public Result ValidateRange(int file_id, Range range){
		
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 获取引用文件
	 * 
	 * Arguments:
	 * file_id    --     文件id
	 * 
	 * 
	 * Returns:
	 * result     --     获取结果
	 * 
	 */
	public Result GetRef_files(int file_id){
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 校验引用标准
	 * 
	 * Arguments:
	 * file_id     --    文件id
	 * ref_files   --    引用标准(已校验)
	 * 
	 * Returns:
	 * result      --     结果
	 * 
	 */
	public Result ValidateRef_files(int file_id, Ref_files ref_files){
		
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 获取术语
	 * 
	 * Arguments:
	 * file_id    --     文件id
	 * 
	 * 
	 * Returns:
	 * result     --     获取结果
	 * 
	 */
	public Result GetTerm_defs(int file_id){
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 校验术语
	 * 
	 * Arguments:
	 * file_id     --    文件id
	 * term_defs    --   术语(已校验)
	 * 
	 * Returns:
	 * result      --     结果
	 * 
	 */
	public Result ValidateTerm_defs(int file_id, Term_defs term_defs){
		
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 获取指标内容
	 * 
	 * Arguments:
	 * file_id    --     文件id
	 * 
	 * 
	 * Returns:
	 * result     --     获取结果
	 * 
	 */
	public Result GetTech_requirements(int file_id){
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 校验指标内容
	 * 
	 * Arguments:
	 * file_id              --    文件id
	 * tech_requirements    --   范围(已校验)
	 * 
	 * Returns:
	 * result               --     结果
	 * 
	 */
	public Result ValidateTech_requirements(int file_id, Tech_requirements tech_requirements){
		
		/*
		 * 逻辑代码
		 */
		Result result = new Result();
		return result;
	}
}
