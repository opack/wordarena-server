package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.slamdunk.wordarena.server.WordArenaServer;
import com.slamdunk.wordarena.server.WordArenaServerException;
import com.slamdunk.wordarena.server.database.LexisService;

/**
 * Vide un lexique et recharge les valeurs initiales pour ce lexique
 */
public class ReloadLexisShellCommand extends AbstractShellCommand {
	private static final int MIN_WORD_LENGTH = 2;
	
	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		try {
			// Lecture du lexique à réinitialiser
			System.out.print("   Lang ISO code : ");
			String lang = in.readLine();
			
			if (lang == null
			|| lang.isEmpty()) {
				return false;
			}
			
			// Lecture des mots à ajouter
			List<String> words = new ArrayList<String>();
			if (!loadFromFile(lang, words)) {
				return false;
			}
			
			// Vidage du lexique mentionné
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
				
				return true;
			} catch (WordArenaServerException e) {
				System.out.println("ERROR : Error while inserting words (" + e.getMessage() + ").");
			}
		} catch (IOException e) {
			System.out.println("ERROR : Error while reading input : " + e.getMessage() + ". Command will not be executed.");
		}
		
		return false;
	}

	/**
	 * Charge le lexique de la langue indiquée vers la liste spécifiée
	 * @param lang
	 * @param words
	 */
	private boolean loadFromFile(String lang, List<String> wordsListToFill) {
		final String lexisFilename = "lexis" + lang + ".txt";
		InputStream lexisStream = ClassLoader.getSystemResourceAsStream(lexisFilename);
		
		try (
			BufferedReader reader = new BufferedReader(new InputStreamReader(lexisStream, "UTF-8"))
		) {
			wordsListToFill.clear();
			String extracted = null;
			
			while ((extracted = reader.readLine()) != null) {
				if (extracted.length() >= MIN_WORD_LENGTH) {
					wordsListToFill.add(extracted);
				}
			}
			
			return true;
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR : Unsupported encoding " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR : Exception while reading lexis file " + lexisFilename);
		}
		
		return false;
	}

}
