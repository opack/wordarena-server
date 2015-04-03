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
		
		// Démarre le serveur dans un thread à part pour que son attente active
		// de clients ne bloque pas le thread du shell
		new Thread() {
			public void run() {
				server.start();
			};
		}.start();
		
		System.out.println("INFO : Server start command issued. Check log for more details.");
		return true;
	}

}
