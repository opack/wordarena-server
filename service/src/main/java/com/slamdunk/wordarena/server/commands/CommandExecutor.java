package com.slamdunk.wordarena.server.commands;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.Server;

/**
 * Représente une commande à exécuter sur le serveur
 */
public interface CommandExecutor {
	
	/**
	 * Retourne la commande associée à cet exécuteur
	 * @return
	 */
	Commands getCommand();
	
	/**
	 * Définit les paramètres à utiliser
	 * @param parameters
	 */
	void setParameters(JsonObject parameters);
	
	/**
	 * Exécute la commande
	 * @param parameters
	 * @param server 
	 */
	void execute(Server server);

	/**
	 * Retourne le résultat de la commande
	 * @return
	 */
	JsonObject getResult();

}
