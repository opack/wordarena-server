package com.slamdunk.wordarena.server.database;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Simplifie l'accès à mongoDB
 */
public class MongoService {
	private MongoDatabase database;
	
	public MongoService(MongoClient mongoClient) {
		database = mongoClient.getDatabase("wordarena");
	}
	
	public MongoCollection<Document> getCollection(String collectionName) {
		return database.getCollection(collectionName);
	}
	
	public MongoDatabase getDatabase() {
		return database;
	}
	
	public boolean clearCollection(String collectionName) {
		MongoCollection<Document> collection = getCollection(collectionName);
		if (collection == null) {
			return false;
		}
		
		// Supprime la collection
		collection.drop();
		
		// Recrée la collection
		database.createCollection(collectionName);
		
		return true;
	}
}
