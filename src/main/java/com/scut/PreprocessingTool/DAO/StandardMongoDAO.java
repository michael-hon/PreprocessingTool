package com.scut.PreprocessingTool.DAO;

import com.scut.PreprocessingTool.Entity.Standard;

public interface StandardMongoDAO {
	//插入标准结构化信息
	public void insertStandard(Standard standard);
	
	//更新标准结构化信息
	public void updateStandard(Standard standard);
	
	//查询标准结构化信息
	public Standard selectStandard(String object_id);
}
