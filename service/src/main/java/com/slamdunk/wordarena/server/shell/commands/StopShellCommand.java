package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class StopShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		// Le serveur ne peut pas être arrêté s'il n'est pas initialisé ou en cours d'écoute
		if (!checkServerInitialized(server)
		|| !checkServerListening(server)) {
			return false;
		}
		
		// Arrêt du serveur
		server.stop();
		
		System.out.println("INFO : Server stop command issued. Check log for more details.");
		
		return true;
	}

}
