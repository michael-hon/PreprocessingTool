package com.scut.PreprocessingTool.Controller;

import java.util.List;

import com.scut.PreprocessingTool.Entity.Result;

/*
 * 结构化数据控制层
 * 
 * 
 */
public class StructureController {
	
	
	/*
	 * 单份文件结构化
	 * 
	 * Arguments:
	 * file_id    --     文件id
	 * 
	 * Returns:
	 * result     --     结构化结果
	 */
	public Result Single_File_Structure(int file_id){
		
		/*
		 * 逻辑代码
		 * 
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 批量文件结构化
	 * Arguments:
	 * files_id    --    文件id   
	 * 
	 * Returns:
	 * result      --    结构化结果
	 * 
	 */
	public Result Batch_File_Structure(List<Integer> files_id){
		/*
		 * 
		 */
		Result result = new Result();
		return result;
	}
	
	
	
	
}
