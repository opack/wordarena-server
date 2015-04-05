package com.slamdunk.wordarena.server.commands.lexis;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.mongodb.MongoClient;
import com.slamdunk.wordarena.server.commands.Command;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Ajoute un mot dans un lexique
 */
public class AddWordCommand implements Command {
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
	
	private JsonObject result;
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(PARAM_LANG, DEFAULT_LANG);
		word = parameters.getString(PARAM_WORD, DEFAULT_WORD).toUpperCase();
	}

	@Override
	public void execute(MongoClient mongoClient) {
		// Ajoute le mot en base
		LexisService mongo = new LexisService(mongoClient, lang);
		mongo.addWord(word);
		
		// Prépare le résultat
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(PARAM_RESULT, true);
		result = builder.build();
	}

	@Override
	public JsonObject getResult() {
		return result;
	}

}
