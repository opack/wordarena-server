package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class StartShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		// Le serveur ne peut pas être démarré s'il n'a pas été initialisé
		if (!checkServerInitialized(server)) {
			return false;
		}
		
		// Lance l'attente des clients dans un thread pour ne pas bloquer le shell
		new Thread() {
			public void run() {
				server.start();
			};
		}.start();
		
		// Si le serveur écoute, alors il est bien démarré
		if (server.isListening()) {
			System.out.println("INFO : Server started.");
		} else {
			System.out.println("ERROR : An error occurred while starting server. Please check log for more details.");
		}
		
		return true;
	}

}
