package com.scut.PreprocessingTool.Service.ServiceImpl;

import java.util.List;

import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;
import com.scut.PreprocessingTool.Service.StandardService;

public class StandardServiceImpl implements StandardService{

	@Override
	public void AnalysisStandard(List<Integer> file_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSourceStandardPath(int file_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnalysisStandardPath(int file_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void CleanStandard(int file_id, String file_content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StructureStandard(int file_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StructureBatchStandard(List<Integer> files_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Standard getStandard(int file_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ValidationBasicInformation(int file_id, String basic_info) {
		// TODO Auto-generated method stub
		
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

}
