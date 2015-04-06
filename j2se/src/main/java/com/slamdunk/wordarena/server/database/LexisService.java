package com.slamdunk.wordarena.server.database;

import static com.mongodb.client.model.Filters.eq;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.COLLECTION;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.ID;
import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.VALID;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.slamdunk.wordarena.server.WordArenaServerException;

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
	
	public void addWord(String word) throws WordArenaServerException {
		// Crée le document à ajouter
		Document doc = new Document()
			.append(ID, word.toUpperCase())
	        .append(VALID, true);
		
		// Ajoute le document
		try {
			collection.insertOne(doc);
		} catch (MongoException e) {
			throwGenericException("Error while inserting the word " + word, e);
		}
	}
	
	public void addWords(List<String> words) throws WordArenaServerException {
		// Crée la liste de documents à ajouter
		List<Document> documents = new ArrayList<Document>();
		for (String word : words) {
			documents.add(new Document()
				.append(ID, word.toUpperCase())
				.append(VALID, true)
			);
		}
		
		// Ajoute les documents
		try {
			collection.insertMany(documents);
		} catch (MongoException e) {
			throwGenericException("Error while inserting the word list", e);
		}
	}

	/**
	 * Supprime le document dont l'ID est le mot spécifié
	 * @param word
	 * @return Nombre de documents supprimés
	 * @throws WordArenaServerException 
	 */
	public long removeWord(String word) throws WordArenaServerException {
		try {
			DeleteResult result = collection.deleteOne(
				eq(ID, word.toUpperCase())
			);
			
			// Retourne le nombre d'éléments supprimés
			return result.getDeletedCount();
		} catch (MongoException e) {
			throwGenericException("Error while inserting the word list", e);
			return 0;
		}
	}
	
	public Document findWord(String word) {
		return collection.find(
			eq(ID, word)
		).first();
	}
}
