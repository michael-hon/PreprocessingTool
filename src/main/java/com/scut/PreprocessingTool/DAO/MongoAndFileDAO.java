package com.scut.PreprocessingTool.DAO;

import com.scut.PreprocessingTool.Entity.Standard;

public interface MongoAndFileDAO {
	
	//建立标准文件与其在MongoDB数据库的结构化信息之间关系
	public void Insert(int file_id, String Mongo_id);
	
	//通过文件ID查询MongoDN数据库ID
	public String selectMongoIDByFileID(int file_id);
}
