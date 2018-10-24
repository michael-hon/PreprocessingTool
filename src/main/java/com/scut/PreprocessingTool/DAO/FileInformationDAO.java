package com.scut.PreprocessingTool.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.openxmlformats.schemas.drawingml.x2006.chart.STXstring;

import com.scut.PreprocessingTool.Entity.FileInformation;

@Mapper
public interface FileInformationDAO {
	
	//插入文件基本信息
	public void insertFileInformation(FileInformation fileInformation);
	
	//保存用户操作源文件记录
	public void insertStaffToFile(@Param("staff_id")int staff_id, 
			                      @Param("file_id")int file_id);
	
	//更新文件基本信息
	public void updateFileInformation(FileInformation fileInformation);
	
	//获取文件名
	public String selectFileName(@Param("file_id")int file_id);
	
	//查询源文件路径
	public String selectSourceFilePathByFileId(@Param("file_id")int file_id);
	
	//查询解析文件路径
	public String selectTxtFilepathByFileId(@Param("file_id")int file_id);
	
	//查询所有文件信息
	public List<FileInformation> selectAllFilesInformation();
	
	//查询文件信息
	public FileInformation selectOneFileInfo(@Param("file_id")int file_id);
	
	//获取文件id
	public int selectFileId(@Param("filename")String filename);
	
	//插入文件名与标准号之间的信息
	public void insertMongoToFile(@Param("file_id")int file_id, 
			                      @Param("ISBN")String ISBN);
}
