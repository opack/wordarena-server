package com.slamdunk.wordarena.server.commands.lexis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public static final String PARAM_PAGE_SIZE = "pageSize";
	public static final int DEFAULT_PAGE_SIZE = 100;
	
	/**
	 * Langue pour laquelle recharger le lexique
	 */
	private String lang;

	/**
	 * Chemin absolu du fichier contenant le lexique
	 */
	private String file;

	/**
	 * Taille d'une page de mots à charger
	 */
	private int pageSize;
	
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
		file = parameters.getString(LexisFields.PARAM_FILE, "lexis" + lang + ".txt");
		pageSize = parameters.getInt(LexisFields.PARAM_PAGE_SIZE, DEFAULT_PAGE_SIZE);
		minWordLength = parameters.getInt(PARAM_MIN_WORD_LENGTH, DEFAULT_MIN_WORD_LENGTH);
	}
	
	@Override
	public void execute(Server server) {
		boolean done = false;
		String details = null;
		List<String> words = new ArrayList<>();

		try (
				InputStream lexisStream = new FileInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(lexisStream, "UTF-8"))
		){
			// Vidage du lexique mentionné
			LexisService lexis = new LexisService(server.getDatabaseClient(), lang);
			if (lexis.clear()) {
				System.out.println("INFO : Lexis for language " + lang + " has been cleared.");
			} else {
				System.out.println("WARN : Lexis for language " + lang + " could not be cleared.");
			}

			do {
				// Lecture d'une page de mots
				words.clear();
				String extracted;

				for (int count = 0; count < pageSize; count++) {
					extracted = reader.readLine();
					if (extracted == null) {
						break;
					}

					words.add(extracted);
				}

				// Ajout des mots
				if (words.isEmpty()) {
					break;
				}
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
			while(!words.isEmpty());

		} catch (FileNotFoundException e) {
			System.out.println("ERROR : Lexis file " + file + " could not be found (" + e.getMessage() + ").");
			done = false;
			details = e.getMessage();
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR : Unsupported encoding " + e.getMessage());
			done = false;
			details = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			done = false;
			details = e.getMessage();
		}

		// Prépare le résultat
		buildResult(done, details);
	}
	
	/**
	 * Charge le lexique de la langue indiquée vers la liste spécifiée
	 * @param wordsListToFill Liste de mots à remplir
	 * @throws IOException 
	 */
	private void loadFromFile(List<String> wordsListToFill) throws IOException {
		try (
			InputStream lexisStream = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(lexisStream, "UTF-8"))
		){
			
			wordsListToFill.clear();
			String extracted;
			
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
