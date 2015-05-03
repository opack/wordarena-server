package com.slamdunk.wordarena.server.commands.lexis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.Server;
import com.slamdunk.wordarena.server.WordArenaServerException;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Charge le lexique de la langue indiqué
 */
public class LoadLexisExecutor extends AbstractCommandExecutor {
	public static final String PARAM_MIN_WORD_LENGTH = "minWordLength";
	public static final int DEFAULT_MIN_WORD_LENGTH = 2;
	
	/**
	 * Langue pour laquelle recharger le lexique
	 */
	private String lang;
	
	/**
	 * Taille minimale des mots à inclure
	 */
	private int minWordLength;
	
	@Override
	public Commands getCommand() {
		return Commands.LEXIS_LOAD;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
		lang = parameters.getString(LexisFields.PARAM_LANG, LexisFields.DEFAULT_LANG);
		minWordLength = parameters.getInt(PARAM_MIN_WORD_LENGTH, DEFAULT_MIN_WORD_LENGTH);
	}
	
	@Override
	public void execute(Server server) {
		boolean done;
		String details = null;
		
		// Lecture des mots à ajouter
		List<String> words = new ArrayList<String>();
		try {
			
			loadFromFile(words);
			done = true;
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR : Unsupported encoding " + e.getMessage());
			done = false;
			details = e.getMessage();
		} catch (IOException e) {
			System.out.println("ERROR : Exception while reading lexis file for lang " + lang);
			done = false;
			details = e.getMessage();
		}
		
		// Vidage du lexique mentionné si le lexique a bien été chargé
		if (done) {
			LexisService lexis = new LexisService(server.getDatabaseClient(), lang);
			if (lexis.clear()) {
				System.out.println("INFO : Lexis for language " + lang + " has been cleared.");
			} else {
				System.out.println("WARN : Lexis for language " + lang + " could not be cleared.");
			}
			
			// Ajout des mots
			try {
				lexis.addWords(words);
				
				System.out.println("INFO : " + words.size() + " words have been added.");
				done = true;
			} catch (WordArenaServerException e) {
				System.out.println("ERROR : Error while inserting words (" + e.getMessage() + ").");
				done = false;
				details = e.getMessage();
			}
		}
		
		// Prépare le résultat
		buildResult(done, details);
	}
	
	/**
	 * Charge le lexique de la langue indiquée vers la liste spécifiée
	 * @param lang
	 * @param words
	 * @throws IOException 
	 */
	private void loadFromFile(List<String> wordsListToFill) throws IOException {
		final String lexisFilename = "lexis" + lang + ".txt";
		InputStream lexisStream = ClassLoader.getSystemResourceAsStream(lexisFilename);
		
		try (
			BufferedReader reader = new BufferedReader(new InputStreamReader(lexisStream, "UTF-8"))
		){
			
			wordsListToFill.clear();
			String extracted = null;
			
			while ((extracted = reader.readLine()) != null) {
				if (extracted.length() >= minWordLength) {
					wordsListToFill.add(extracted);
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
}
