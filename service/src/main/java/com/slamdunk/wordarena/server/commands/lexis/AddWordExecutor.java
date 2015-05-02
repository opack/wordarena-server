package com.slamdunk.wordarena.server.commands.lexis;

import javax.json.JsonObject;

import com.mongodb.MongoClient;
import com.slamdunk.wordarena.server.WordArenaServerException;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Ajoute un mot dans un lexique
 */
public class AddWordExecutor extends AbstractCommandExecutor {
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
		return Commands.LEXIS_ADD;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(LexisFields.PARAM_LANG, LexisFields.DEFAULT_LANG);
		word = parameters.getString(LexisFields.PARAM_WORD, LexisFields.DEFAULT_WORD).toUpperCase();
	}

	@Override
	public void execute(MongoClient mongoClient) {
		boolean done;
		String details = null;
		LexisService mongo = new LexisService(mongoClient, lang);
		
		try {
			// Ajoute le mot en base
			mongo.addWord(word);
			done = true;
		} catch (WordArenaServerException e) {
			done = false;
			details = e.getMessage();
		}
		
		// Prépare le résultat
		buildResult(done, details);
	}

}
