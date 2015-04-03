package com.slamdunk.wordarena.server.commands;


/**
 * Gère les différentes commandes reçues en en transmettant le traitement à l'objet adéquat
 */
public class CommandProcessor {

	/**
	 * Traite une commande au format JSON suivant :
	 * {
	 * 	  command : '',
	 *    parameters : {}
	 * }
	 * @param command
	 * @return
	 */
    public String process(String command) {
    	// TODO Parse le JSON reçu
    	
    	// TODO Délègue le traitement de la commande et récupère le résultat
        String result = null;

        return result;
    }
}