package com.slamdunk.wordarena.server.commands.server;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.Server;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;

/**
 * Ajoute un mot dans un lexique
 */
public class ShutdownExecutor extends AbstractCommandExecutor {
	
	@Override
	public Commands getCommand() {
		return Commands.SERVER_SHUTDOWN;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
	}

	@Override
	public void execute(Server server) {
		// Arrête d'attendre des clients
		server.stop();
		
		// Prépare le résultat
		buildResult(true);
	}
}
