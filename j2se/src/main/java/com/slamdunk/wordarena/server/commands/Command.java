package com.slamdunk.wordarena.server.commands;

import javax.json.JsonObject;

import com.mongodb.MongoClient;

/**
 * Représente une commande à exécuter sur le serveur
 */
public interface Command {
	
	/**
	 * Définit les paramètres à utiliser
	 * @param parameters
	 */
	void setParameters(JsonObject parameters);
	
	/**
	 * Exécute la commande
	 * @param parameters
	 * @param mongoClient 
	 */
	void execute(MongoClient mongoClient);

	/**
	 * Retourne le résultat de la commande
	 * @return
	 */
	JsonObject getResult();

}
