package com.scut.PreprocessingTool.util;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;



public class MongoDBConnect {
	private volatile static MongoClient mongoClient=null;
	private MongoDBConnect(){
		
	}
	public static MongoClient getInstance(){
		if(mongoClient == null){
			synchronized(MongoDBConnect.class){
				if(mongoClient == null){
					CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
					mongoClient = new MongoClient("127.0.0.1", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
				}
			}
		}
		return mongoClient;
	}
}
