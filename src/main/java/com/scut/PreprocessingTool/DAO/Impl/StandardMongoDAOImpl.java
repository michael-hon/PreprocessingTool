package com.scut.PreprocessingTool.DAO.Impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.scut.PreprocessingTool.DAO.StandardMongoDAO;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.util.MongoDBConnect;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class StandardMongoDAOImpl implements StandardMongoDAO{

	@Override
	public void insertStandard(Standard standard) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = MongoDBConnect.getInstance();
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Standard> collections = database.getCollection("Standard", Standard.class);
		collections.insertOne(standard);
		
	}

	@Override
	public void updateStandard(Standard standard) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = MongoDBConnect.getInstance();
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Standard> collections = database.getCollection("Standard", Standard.class);
		collections.replaceOne(eq("ISBN", standard.getISBN()), standard);
	}

	@Override
	public Standard selectStandard(String ISBN) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = MongoDBConnect.getInstance();
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Standard> collections = database.getCollection("Standard", Standard.class);
		Standard standard = collections.find(eq("ISBN",ISBN)).first();
		return standard;
	}

}
