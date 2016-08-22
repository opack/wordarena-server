package com.slamdunk.wordarena.server.shell.commands.lexis;

import java.io.BufferedReader;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

/**
 * Vide un lexique et recharge les valeurs initiales pour ce lexique
 */
public class LexisLoadCommand extends AbstractShellCommand {
	private static final String COMMAND_NAME = "LEXIS_LOAD";
	private static final String PARAM_LANG = "lang";
	private static final String PARAM_FILE = "file";
	private static final String PARAM_PAGE_SIZE = "pageSize";
	private static final String PARAM_MIN_WORD_LENGTH = "minWordLength";
	
	@Override
	public boolean execute(BufferedReader in) {
		String lang;
		String file;
		String pageSize;
		String minWordLength;
		try {
			// Lecture du lexique à réinitialiser
			System.out.print("   Lang ISO code : ");
			lang = in.readLine();		
		
			if (lang == null
			|| lang.isEmpty()) {
				return false;
			}

			// Lecture du chemin du lexique
			System.out.print("   File to load : ");
			file = in.readLine();

			if (file == null
			|| file.isEmpty()) {
				return false;
			}

			// Taille d'une page lors de la lecture du fichier
			System.out.print("   Page size : ");
			pageSize = in.readLine();

			if (pageSize == null
			|| pageSize.isEmpty()) {
				return false;
			}
			
			// Lecture de la taille minimale des mots à prendre en compte
			System.out.print("   Min word length : ");
			minWordLength = in.readLine();
			
			if (minWordLength == null
			|| minWordLength.isEmpty()) {
				return false;
			}
		} catch (IOException e) {
			System.out.println("ERROR : Unable to read parameters from console.");
			return false;
		}
		
		// Création de la commande
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(PARAM_LANG, lang);
		builder.add(PARAM_FILE, file);
		builder.add(PARAM_PAGE_SIZE, pageSize);
		builder.add(PARAM_MIN_WORD_LENGTH, Integer.parseInt(minWordLength));
		JsonObject command = builder.build();
		
		// Envoi de la commande au serveur
		return sendCommand(COMMAND_NAME, command) != null;
	}
}
