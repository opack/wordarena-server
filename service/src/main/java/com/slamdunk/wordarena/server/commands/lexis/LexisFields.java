package com.slamdunk.wordarena.server.commands.lexis;

/**
 * Constantes des champs d'un document de la collection "lexisXX"
 */
public class LexisFields {
	/**
	 * Nom de la collection	
	 */
	public static final String COLLECTION = "lexis";
	
	/**
	 * Nom des différents champs des documents
	 */
	public static final String ID = "_id";
	public static final String VALID = "valid";
	
	/**
	 * Nom des paramètres utilisables lors des commandes.
	 * Ici sont présentés les paramètres communs. Si des paramètres
	 * spécifiques sont nécessaires, ils seront définis au niveau de
	 * la commande.
	 */
	public static final String PARAM_LANG = "lang";
	public static final String DEFAULT_LANG = "FR";

	public static final String PARAM_FILE = "file";
	
	public static final String PARAM_WORD = "word";
	public static final String DEFAULT_WORD = "";
}
