package com.scut.PreprocessingTool.Controller;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scut.PreprocessingTool.Entity.Result;
import com.scut.PreprocessingTool.Service.StandardService;
import com.scut.PreprocessingTool.enums.ResultEnum;
import com.scut.PreprocessingTool.util.FileUtil;

/*
 * 数据清洗控制层
 */
@RestController
public class TextCleanController {
	
	
	/*
	 *获取PDF源文件 
	 * 
	 * Arguments:
	 * file_id   --    文件id 
	 * 
	 * Returns:
	 * result    --     结果
	 * 
	 */
	
	@Resource
	StandardService standardService;
	
	
	
	@RequestMapping(value="/getPDFFile", method=RequestMethod.POST)
	public Result GetPDFFile(@RequestParam(value="file_id", required=true)int file_id){
		
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
	@RequestMapping(value="/getTxtFile", method=RequestMethod.POST)
	public Result GetTXTFile(@RequestParam(value="file_id", required=true)int file_id){
		/*
		 * 逻辑代码
		 * 
		 */
		String txtFilePath = standardService.getAnalysisStandardPath(file_id);
		String file_content = FileUtil.readToString(txtFilePath);
		Result result = new Result(ResultEnum.SUCCESS, file_content);
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
		standardService.CleanStandard(file_id, file_content);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
	
	/*
	 * 
	 */
	
}
