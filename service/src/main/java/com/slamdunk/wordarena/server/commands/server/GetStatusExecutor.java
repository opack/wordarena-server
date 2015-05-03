package com.slamdunk.wordarena.server.commands.server;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.slamdunk.wordarena.server.Server;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;

/**
 * Ajoute un mot dans un lexique
 */
public class GetStatusExecutor extends AbstractCommandExecutor {
	private static final String PARAM_LISTENING = "listening";
	private static final String PARAM_ALLOW_EXTERNAL_CLIENTS = "allowExternalClients";
	
	@Override
	public Commands getCommand() {
		return Commands.SERVER_STATUS;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
	}

	@Override
	public void execute(Server server) {
		// Récupère les infos du serveur (allowExternalClients...)
		JsonObjectBuilder status = Json.createObjectBuilder();
		status.add(PARAM_LISTENING, server.isListening());
		status.add(PARAM_ALLOW_EXTERNAL_CLIENTS, server.isAllowExternalClients());
		
		// Prépare le résultat
		buildResult(true, status.build());
	}
}
