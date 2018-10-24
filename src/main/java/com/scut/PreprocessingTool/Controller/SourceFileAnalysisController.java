package com.scut.PreprocessingTool.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.scut.PreprocessingTool.DAO.FileInformationDAO;
import com.scut.PreprocessingTool.Entity.FileInformation;
import com.scut.PreprocessingTool.Entity.Result;
import com.scut.PreprocessingTool.Entity.Staff;
import com.scut.PreprocessingTool.Service.StandardService;
import com.scut.PreprocessingTool.enums.ResultEnum;
import com.scut.PreprocessingTool.util.DateFormatUtil;
import com.scut.PreprocessingTool.util.FileInfoUtil;
import com.scut.PreprocessingTool.util.PdfboxUtil;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveTypeAwareAssigner;

@RestController
public class SourceFileAnalysisController {
	
	
	private final String UPLOADED_FOLDER = "E://upload_//";
	
	private final String QiyeTxt_FOLDER = "E://QiyeTxt//";
	@Resource
	private StandardService standardService;
	
	@Resource
	private FileInformationDAO fileInfoDao;
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
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	public Result uploadFile(@RequestParam(value="files", required=true)MultipartFile[] files,
			                 HttpSession session){
		/*
		 * 逻辑代码
		 */
		for(MultipartFile multipartFile:files){
			//判断文件类型，只能是pdf、doc、docx
			if(!FileInfoUtil.judgeFileFormat(multipartFile.getOriginalFilename())){
				return new Result(ResultEnum.FILE_FORMAT_ERROR);
			}
		}
		for (MultipartFile multipartFile : files) {
			try{
			//保存文件在服务器
			String originalFilename = multipartFile.getOriginalFilename();
			byte[] bytes = multipartFile.getBytes();
			String source_filepath = UPLOADED_FOLDER + multipartFile.getOriginalFilename();//包括后面的pdf扩展格式
			Path path = Paths.get(source_filepath);
			Files.write(path, bytes);
			
			//保存文件信息
			String filename = originalFilename.substring(0,originalFilename.lastIndexOf('.'));//不包括后面的文件格式
			String upload_time = DateFormatUtil.formatDate(System.currentTimeMillis());
			FileInformation fileInfo = new FileInformation(filename, 
					upload_time, source_filepath, "", 0, 0, 0, 0);
			standardService.InsertInfo(fileInfo);
			
			//记录上传文件的用户
			Staff staff = (Staff) session.getAttribute("staff");
			int staff_if = staff==null?90000:staff.getStaff_id();
			int file_id = standardService.ObtainFileId(filename);
			standardService.recordUserToFile(staff_if, file_id);
			}
			catch(IOException e){
				e.printStackTrace();
				return new Result(ResultEnum.SERVER_ERROR);
			}
			
		}
		List<FileInformation> filesInfo = fileInfoDao.selectAllFilesInformation();
		Result result = new Result(ResultEnum.SUCCESS, filesInfo);
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
	@RequestMapping(value="/analysisSingleText", method=RequestMethod.POST)
	public Result AnalysisSingleText(@RequestParam(value="file_id", required=true)int file_id){
		/*
		 * 逻辑代码
		 */
		standardService.AnalysisSingleStandard(file_id);
		Result result = new Result(ResultEnum.SUCCESS);
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
	@RequestMapping(value="/analysisBatchText", method=RequestMethod.POST)
	public Result AnalysisBatchText(@RequestParam(value="file_id", required=true)List<Integer>file_id){
		/*
		 * 逻辑代码
		 */
		standardService.AnalysisBatchStandard(file_id);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
	
	
}
