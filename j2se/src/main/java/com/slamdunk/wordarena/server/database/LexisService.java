package com.slamdunk.wordarena.server.database;

import static com.mongodb.client.model.Filters.eq;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.COLLECTION;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.ID;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.VALID;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * Service simplifiant la gestion de la collection "lexisXX" où
 * XX est le code de la langue. Ex : "lexisFR"
 */
public class LexisService extends MongoService {
	private final String collectionName;
	private MongoCollection<Document> collection;

	public LexisService(MongoClient mongoClient, String langISOCode) {
		super(mongoClient);
		collectionName = COLLECTION + langISOCode.toUpperCase();
		collection = getCollection(collectionName);
	}
	
	/**
	 * Vide la collection
	 */
	public boolean clear() {
		return clearCollection(collectionName);
	}
	
	public void addWord(String word) {
		// Crée le document à ajouter
		Document doc = new Document()
			.append(ID, word.toUpperCase())
	        .append(VALID, true);
		
		// Ajoute le document
		collection.insertOne(doc);
	}
	
	public void addWords(List<String> words) {
		// Crée la liste de documents à ajouter
		List<Document> documents = new ArrayList<Document>();
		for (String word : words) {
			documents.add(new Document()
				.append(ID, word.toUpperCase())
				.append(VALID, true)
			);
		}
		
		// Ajoute les documents
		collection.insertMany(documents);
	}
	
	public Document findWord(String word) {
		return collection.find(
			eq(ID, word)
		).first();
	}
}
