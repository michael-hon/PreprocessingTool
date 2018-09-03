package com.scut.PreprocessingTool.DAO;

import java.util.List;

import com.scut.PreprocessingTool.Entity.FileInformation;

public interface FileInformationDAO {
	
	//插入文件基本信息
	public void insertFileInformation(FileInformation fileInformation);
	
	//更新文件基本信息
	public void updateFileInformation(FileInformation fileInformation);
	
	//查询源文件路径
	public String selectSourceFilePathByFileId(int file_id);
	
	//查询解析文件路径
	public String selectTxtFilepathByFileId(int file_id);
	
	//查询所有文件信息
	public List<FileInformation> selectAllFilesInformation();
}
