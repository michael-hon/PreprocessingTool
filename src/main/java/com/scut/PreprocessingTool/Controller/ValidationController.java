package com.scut.PreprocessingTool.Controller;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.scut.PreprocessingTool.DAO.FileInformationDAO;
import com.scut.PreprocessingTool.Entity.FileInformation;
import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Result;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;
import com.scut.PreprocessingTool.Entity.standard_info;
import com.scut.PreprocessingTool.Service.StandardService;
import com.scut.PreprocessingTool.enums.ResultEnum;

/*
 * 数据校验控制层
 */
@RestController
public class ValidationController {

	@Resource
	private StandardService standardService;
	
	@Resource
	private FileInformationDAO fileInfoDao;
	/*
	 * 获取文件列表
	 * 
	 * 
	 * Returns:
	 * result   --    获取结果
	 */
	@RequestMapping(value="/getAllList", method=RequestMethod.POST)
	public Result GetFileList(){
		/*
		 * 逻辑代码
		 */
		List<FileInformation> filesInfo = fileInfoDao.selectAllFilesInformation();
		Result result = new Result(ResultEnum.SUCCESS, filesInfo);
		return result;
	}
	
//	/*
//	 * 获取PDF源文件
//	 * 
//	 * Arguments:
//	 * file_id  --   文件id
//	 * 
//	 * Returns:
//	 * result   --   结果
//	 */
//	public Result GetPDFFile(){
//		
//		/*
//		 * 逻辑代码
//		 */
//		Result result = new Result();
//		return result;
//	}
	
	/*
	 * 获取标准文件基本信息
	 * 
	 * Arguments:
	 * file_id    --    文件id
	 * 
	 * Returns:
	 * result     --    获取结果
	 */
	@RequestMapping(value="/getBasicInfo", method=RequestMethod.POST)
	public Result GetBasicInformation(@RequestParam(value="file_id", required=true)int file_id){
		/*
		 * 逻辑代码
		 */
		Standard standard = standardService.getStandard(file_id);
		standard_info std_info = new standard_info();
		std_info.setNumber(standard.getISBN());
		std_info.setC_name(standard.getCh_title());
		std_info.setE_name(standard.getEn_title());
		std_info.setStatus(standard.getIs_valid());
		std_info.setCcs(standard.getCCS());
		std_info.setPublish_date(standard.getPublish_date());
		std_info.setCarray_out_date(standard.getCarray_out_date());
		Result result = new Result(ResultEnum.SUCCESS, std_info);
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
	@RequestMapping(value="/validateBasicInfo", method=RequestMethod.POST)
	public Result ValidateBasicInformation(@RequestParam(value="file_id", required=true)int file_id,
			                               @RequestParam(value="structure_info", required=true)String structure_info){
		/*
		 * 逻辑代码
		 * 
		 */
		standardService.ValidationBasicInformation(file_id, structure_info); 
		Result result = new Result(ResultEnum.SUCCESS);
		
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
	
	/*
	 * 保存修改结构化内容（该内容是结构化所有内容，包括基本信息、指标、术语等等，且以JSON格式上传）
	 */
	@RequestMapping(value="/SaveStandard", method=RequestMethod.POST)
	public Result SaveStandard(@RequestParam(value="file_id", required=true)int file_id, 
			                   @RequestParam(value="standard_str", required=true)String standard_str){
		standardService.SaveStandard(file_id, standard_str);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
