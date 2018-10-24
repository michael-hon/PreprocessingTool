package com.scut.PreprocessingTool.Service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.scut.PreprocessingTool.DAO.FileInformationDAO;
import com.scut.PreprocessingTool.DAO.StandardMongoDAO;
import com.scut.PreprocessingTool.DAO.standard_infoDAO;
import com.scut.PreprocessingTool.Entity.FileInformation;
import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;
import com.scut.PreprocessingTool.Entity.standard_info;
import com.scut.PreprocessingTool.Service.StandardService;
import com.scut.PreprocessingTool.util.FileUtil;
import com.scut.PreprocessingTool.util.POIUtil;
import com.scut.PreprocessingTool.util.PdfboxUtil;
import com.scut.PreprocessingTool.util.StructuredCountry;
import com.scut.PreprocessingTool.util.StructuredHelp;

public class StandardServiceImpl implements StandardService{
	
	
	private final String QiyeTxt_FOLDER = "E://QiyeTxt//";
	
	@Resource
	private FileInformationDAO fileInfoDao;
	
	@Resource
	private standard_infoDAO sid;
	
	@Resource
	private StandardMongoDAO stdMongo;
	
	@Override
	public void InsertInfo(FileInformation fileInfo){
		fileInfoDao.insertFileInformation(fileInfo);
	}
	
	@Override
	public void recordUserToFile(int staff_id, int file_id){
		fileInfoDao.insertStaffToFile(staff_id, file_id);
	}
	
	@Override
	public int ObtainFileId(String filename){
		return fileInfoDao.selectFileId(filename);
	}


	@Override
	public String getSourceStandardPath(int file_id) {
		// TODO Auto-generated method stub
		return fileInfoDao.selectSourceFilePathByFileId(file_id);
	}

	@Override
	public String getAnalysisStandardPath(int file_id) {
		// TODO Auto-generated method stub
		return fileInfoDao.selectTxtFilepathByFileId(file_id);
	}

	@Override
	public void CleanStandard(int file_id, String file_content) {
		// TODO Auto-generated method stub
		//替换文件内容
		String txtPath = fileInfoDao.selectTxtFilepathByFileId(file_id);
		FileUtil.replaceTxt(txtPath, file_content);
		//更新数据库，更改文件信息状态(已清洗)
		FileInformation fileInfoTemp = fileInfoDao.selectOneFileInfo(file_id);
		fileInfoTemp.setIs_clean(1);
		fileInfoDao.updateFileInformation(fileInfoTemp);
	}

	@Override
	public void StructureStandard(int file_id) {
		// TODO Auto-generated method stub
		//获取文本文件路径
		String txtPath = fileInfoDao.selectTxtFilepathByFileId(file_id);
		StructuredCountry sta = new StructuredCountry();
		try{
		//结构化
		sta.StructuredFile(txtPath);
		//更改数据库，更改文件信息状态(已结构化)
		FileInformation fileInfo = fileInfoDao.selectOneFileInfo(file_id);
		fileInfo.setIs_structure(1);
		fileInfoDao.updateFileInformation(fileInfo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void StructureBatchStandard(List<Integer> files_id) {
		// TODO Auto-generated method stub
		for (Integer file_id : files_id) {
			StructureStandard(file_id);
		}
	}

	@Override
	public Standard getStandard(int file_id) {
		// TODO Auto-generated method stub
		String filename = fileInfoDao.selectFileName(file_id);
		List<standard_info> standard_infos = sid.select();
		StructuredHelp sth = new StructuredHelp();
		standard_info std_info = sth.getRightStandard_info(filename, standard_infos);
		String ISBN = std_info.getNumber();
		Standard standard = stdMongo.selectStandard(ISBN);
		return standard;
	}

	@Override
	public void ValidationBasicInformation(int file_id, String basic_info) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		standard_info std_info = gson.fromJson(basic_info, standard_info.class);
		
	}

	@Override
	public void ValidationRange(int file_id, Range range) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ValidationRef_files(int file_id, Ref_files ref_files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ValidationTerm_defs(int file_id, Term_defs term_defs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ValidationTech_requirements(int file_id, Tech_requirements tech_requirements) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnalysisSingleStandard(int file_id) {
		// TODO Auto-generated method stub
		String filename = fileInfoDao.selectFileName(file_id);
		String sourcePath = fileInfoDao.selectSourceFilePathByFileId(file_id);
		String text_filepath = "";
		if(sourcePath.endsWith("pdf")||sourcePath.endsWith("PDF")){
		     /*
		      * 此处又分为两种情况：国家标准和企业标准。国家标准需要使用OCR，企业标准使用pdfBox库	
		      */
			 /*
			  * 这里假设上传文件为企业标准
			  */
			
			text_filepath = QiyeTxt_FOLDER + filename + ".txt";
			//解析
			PdfboxUtil.transformPdfToTxt(sourcePath, text_filepath);

			/*
			 * 国家标准，需要使用OCR技术
			 */
			
			
		}
		else{
			/*
			 * doc文件
			 */
			
			if(sourcePath.endsWith("doc")||sourcePath.endsWith("DOC")){
				POIUtil.convertToText(sourcePath, filename);
			}
			/*
			 * docx文件
			 */
			else{
				
			}
			text_filepath = filename + ".txt";
		}
		//更新文件状态信息
		FileInformation fileInfoTemp = fileInfoDao.selectOneFileInfo(file_id);
		fileInfoTemp.setText_filepath(text_filepath);
		fileInfoTemp.setIs_analysis(1);
		fileInfoDao.updateFileInformation(fileInfoTemp);
	}

	@Override
	public void AnalysisBatchStandard(List<Integer> file_id) {
		// TODO Auto-generated method stub
		for (Integer id : file_id) {
			AnalysisSingleStandard(id);
		}
	}

	@Override
	public void SaveStandard(int file_id, String standard_str) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Standard standard = gson.fromJson(standard_str, Standard.class);
		stdMongo.updateStandard(standard);
		//更改数据库，更改文件信息状态(已校验)
		FileInformation fileInfo = fileInfoDao.selectOneFileInfo(file_id);
		fileInfo.setIs_validation(1);
		fileInfoDao.updateFileInformation(fileInfo);
	}

}
