package com.scut.PreprocessingTool.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scut.PreprocessingTool.Entity.Result;
import com.scut.PreprocessingTool.Service.StandardService;
import com.scut.PreprocessingTool.enums.ResultEnum;

/*
 * 结构化数据控制层
 * 
 * 
 */
@RestController
public class StructureController {
	
	@Resource
	private StandardService standardService;
	/*
	 * 单份文件结构化
	 * 
	 * Arguments:
	 * file_id    --     文件id
	 * 
	 * Returns:
	 * result     --     结构化结果
	 */
	@RequestMapping(value="/singleStructure", method=RequestMethod.POST)
	public Result Single_File_Structure(@RequestParam(value="file_id", required=true)int file_id){
		
		/*
		 * 逻辑代码
		 * 
		 */
		standardService.StructureStandard(file_id);
		Result result = new Result(ResultEnum.SUCCESS);
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
	@RequestMapping(value="/batchStructure", method=RequestMethod.POST)
	public Result Batch_File_Structure(List<Integer> files_id){
		/*
		 * 逻辑代码 
		 */
		standardService.StructureBatchStandard(files_id);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
	
	
	
	
}
