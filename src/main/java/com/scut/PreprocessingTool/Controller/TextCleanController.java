package com.scut.PreprocessingTool.Controller;

import com.scut.PreprocessingTool.Entity.Result;

/*
 * 数据清洗控制层
 */

public class TextCleanController {
	
	
	/*
	 *获取PDF源文件 
	 * 
	 * Arguments:
	 * file_id   --    文件id 
	 * 
	 * Returns:
	 * result    --     清洗结果
	 * 
	 */
	
	
	public Result GetPDFFile(int file_id){
		
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		return result;
	}
	
	
	
	/*
	 * 获取解析文件
	 * 
	 * Arguments:
	 * file_id   --    文件id
	 * 
	 * 
	 * Returns:
	 * result    --    获取结果
	 */
	
	public Result GetTXTFile(int file_id){
		/*
		 * 逻辑代码
		 * 
		 */
		Result result = new Result();
		return result;
	}
	
	/*
	 * 数据清洗
	 * 
	 * Arguments:
	 * file_id        --   文件id
	 * file_content   --   文档内容(已清洗)
	 * 
	 * Returns:
	 * result         --   结果
	 * 
	 */
	public Result CleanFile(int file_id, String file_content){
		/*
		 * 逻辑代码
		 */
		
		Result result = new Result();
		return result;
	}
	
	/*
	 * 
	 */
	
}
