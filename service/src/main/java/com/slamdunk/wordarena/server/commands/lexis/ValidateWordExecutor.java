package com.slamdunk.wordarena.server.commands.lexis;

import static com.slamdunk.wordarena.server.commands.lexis.LexisFields.VALID;

import javax.json.JsonObject;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Valide un mot en indiquant s'il fait ou non partie du lexique
 */
public class ValidateWordExecutor extends AbstractCommandExecutor {
	/**
	 * Langue dans laquelle chercher le mot
	 */
	private String lang;
	
	/**
	 * Mot à rechercher
	 */
	private String word;
	
	@Override
	public Commands getCommand() {
		return Commands.LEXIS_VALIDATE;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(LexisFields.PARAM_LANG, LexisFields.DEFAULT_LANG);
		word = parameters.getString(LexisFields.PARAM_WORD, LexisFields.DEFAULT_WORD).toUpperCase();
	}

	@Override
	public void execute(MongoClient mongoClient) {
		// Recherche le mot en base
		LexisService mongo = new LexisService(mongoClient, lang);
		Document doc = mongo.findWord(word);
		
		// Détermine sa validité
		boolean valid = doc != null && doc.getBoolean(VALID, true);
		
		// Prépare le résultat
		buildResult(valid);
	}

}
