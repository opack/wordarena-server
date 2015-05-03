package com.slamdunk.wordarena.server.commands.server;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.Server;
import com.slamdunk.wordarena.server.commands.AbstractCommandExecutor;
import com.slamdunk.wordarena.server.commands.Commands;

/**
 * Ajoute un mot dans un lexique
 */
public class RefuseExternalClientsExecutor extends AbstractCommandExecutor {
	
	@Override
	public Commands getCommand() {
		return Commands.SERVER_STOP;
	}
	
	@Override
	public void setParameters(JsonObject parameters) {
	}

	@Override
	public void execute(Server server) {
		// Modifie le flag dans le serveur
		server.setAllowExternalClients(false);
		
		// Prépare le résultat
		buildResult(true);
	}

}
