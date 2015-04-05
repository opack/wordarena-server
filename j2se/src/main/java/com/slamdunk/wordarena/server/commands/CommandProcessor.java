package com.slamdunk.wordarena.server.commands;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.mongodb.MongoClient;

/**
 * Gère les différentes commandes reçues en en transmettant le traitement à l'objet adéquat
 */
public class CommandProcessor {
	/**
	 * Identifiant du client à utiliser dans la log
	 */
	private String logClientId;
	
	public void setLogClientId(String logClientId) {
		this.logClientId = logClientId;
	}

	/**
	 * Traite une commande au format JSON suivant :
	 * {
	 * 	  command : '',
	 *    parameters : {}
	 * }
	 * @param jsonCommand
	 * @param mongoClient 
	 * @return
	 */
    public String process(String jsonCommand, MongoClient mongoClient) {
    	// Parse le JSON reçu pour extraire les infos de travail
    	StringReader reader = new StringReader(jsonCommand);
    	JsonReader json = Json.createReader(reader);
    	JsonObject root = json.readObject();
    	
    	String commandName = root.getString("command", "");
    	JsonObject parameters = root.getJsonObject("parameters");
    	
    	json.close();
    	
    	// Crée la commande adéquate
    	Command command = CommandFactory.create(commandName);
    	command.setParameters(parameters);
    	System.out.println("INFO [" + logClientId + "] : Processing " + commandName + "...");
    	
    	// Exécute la commande
    	command.execute(mongoClient);
    	
    	// Retourne le résultat
    	System.out.println("INFO [" + logClientId + "] : Result : " + command.getResult().toString());
        return command.getResult().toString();
    }
}