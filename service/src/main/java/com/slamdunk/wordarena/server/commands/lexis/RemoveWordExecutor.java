package com.slamdunk.wordarena.server.commands.lexis;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.Server;
import com.slamdunk.wordarena.server.WordArenaServerException;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Ajoute un mot dans un lexique
 */
public class RemoveWordExecutor extends AbstractCommandExecutor {
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
		return Commands.LEXIS_REMOVE;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(LexisFields.PARAM_LANG, LexisFields.DEFAULT_LANG);
		word = parameters.getString(LexisFields.PARAM_WORD, LexisFields.DEFAULT_WORD).toUpperCase();
	}

	@Override
	public void execute(Server server) {
		boolean done;
		LexisService mongo = new LexisService(server.getDatabaseClient(), lang);
		try {
			// Supprime le mot en base.
			done = mongo.removeWord(word) == 1;
		} catch (WordArenaServerException e) {
			done = false;
		}
		
		// Prépare le résultat
		buildResult(done);
	}

}
