package com.scut.PreprocessingTool.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scut.PreprocessingTool.Entity.standard_info;


@Mapper
public interface standard_infoDAO {
	
	public List<standard_info> select();
	
}
