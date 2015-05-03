package com.slamdunk.wordarena.server.shell.commands;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.shell.ServerConnection;

public abstract class AbstractShellCommand implements ShellCommand {
	
	/**
	 * Envoie la requête au serveur wordarena sur localhost
	 */
	protected JsonObject sendCommand(String name) {
		return sendCommand(name, "{}");
	}
	
	/**
	 * Envoie la requête au serveur wordarena sur localhost
	 */
	protected JsonObject sendCommand(String name, JsonObject parameters) {
		return sendCommand(name, parameters.toString());
	}
	
	/**
	 * Envoie la requête au serveur wordarena sur localhost
	 */
	protected JsonObject sendCommand(String name, String parameters) {
		// Envoie la commande au serveur
		ServerConnection server = new ServerConnection();
		JsonObject response = server.sendCommand(name, parameters);
		
		// Affiche le résultat
		if (response != null) {
			System.out.println(response.toString());
		}
		
		// Retourne le résultat
		return response;
	}
}
