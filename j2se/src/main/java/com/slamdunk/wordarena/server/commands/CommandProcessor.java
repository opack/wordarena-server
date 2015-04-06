package com.slamdunk.wordarena.server.commands;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import com.mongodb.MongoClient;

/**
 * Gère les différentes commandes reçues en en transmettant le traitement à l'objet adéquat
 */
public class CommandProcessor {
	public static final String PARAM_RESULT = "result";
	public static final String PARAM_DETAILS = "details";
	
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
    	CommandExecutor command = CommandFactory.create(commandName);
    	if (command == null) {
    		JsonObjectBuilder builder = Json.createObjectBuilder();
    		builder.add(PARAM_RESULT, false);
    		builder.add(PARAM_DETAILS, "Failed to create command " + commandName);
    		return builder.build().toString();
    	}
    	command.setParameters(parameters);
    	System.out.println("INFO [" + logClientId + "] : Processing " + commandName + "...");
    	
    	// Exécute la commande
    	command.execute(mongoClient);
    	
    	// Retourne le résultat
    	System.out.println("INFO [" + logClientId + "] : Result : " + command.getResult().toString());
        return command.getResult().toString();
    }
}