package com.slamdunk.wordarena.server.commands.lexis;

import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.VALID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.slamdunk.wordarena.server.commands.Command;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Valide un mot en indiquant s'il fait ou non partie du lexique
 */
public class ValidateWordCommand implements Command {
	public static final String PARAM_LANG = "lang";
	private static final String DEFAULT_LANG = "FR";
	
	public static final String PARAM_WORD = "word";
	private static final String DEFAULT_WORD = "";
	
	public static final String PARAM_RESULT = "result";
	
	/**
	 * Langue dans laquelle chercher le mot
	 */
	private String lang;
	
	/**
	 * Mot à rechercher
	 */
	private String word;
	
	/**
	 * Résultat indiquant si le mot est valide ou non
	 */
	private boolean valid;
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(PARAM_LANG, DEFAULT_LANG);
		word = parameters.getString(PARAM_WORD, DEFAULT_WORD).toUpperCase();
	}

	@Override
	public void execute(MongoClient mongoClient) {
		// Recherche le mot en base
		LexisService mongo = new LexisService(mongoClient, lang);
		Document doc = mongo.findWord(word);
		
		// Détermine sa validité
		valid = doc != null && doc.getBoolean(VALID, false);
	}

	@Override
	public JsonObject getResult() {
		JsonObjectBuilder result = Json.createObjectBuilder();
		result.add(PARAM_RESULT, valid);
		return result.build();
	}

}
