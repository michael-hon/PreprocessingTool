package com.scut.PreprocessingTool.Service;

import java.util.List;

import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;

public interface StandardService {
	//标准解析
	public void AnalysisStandard(List<Integer> file_id);
	
	//获取源文件路径
	public String getSourceStandardPath(int file_id);
	
	//获取解析文件路径
	public String getAnalysisStandardPath(int file_id);
	
	//标准清洗，保存清洗内容
	public void CleanStandard(int file_id, String file_content);
	
	//结构化文档
	public void StructureStandard(int file_id);
	
	//结构化文档(批量)
	public void StructureBatchStandard(List<Integer> files_id);
	
	//从MongoDB数据库获取结构化文档（数据校验时，均调用此接口获取结构化数据）
	public Standard getStandard(int file_id);
	
	//校验标准基本信息(保存校验结果)
	public void ValidationBasicInformation(int file_id, String basic_info);
	
	//校验文档范围内容
	public void ValidationRange(int file_id, Range range);
	
	//校验文档范围内容
	public void ValidationRef_files(int file_id, Ref_files ref_files);
	
	//校验文档术语
	public void ValidationTerm_defs(int file_id, Term_defs term_defs);
	
	//校验指标内容
	public void ValidationTech_requirements(int file_id, Tech_requirements tech_requirements);
	
}
