package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class ExitShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		if (server.isListening()) {
			
			// Si le serveur tourne on l'arrête
			if (!new StopShellCommand().execute(server, in)) {
				
				// S'il y a eut un souci pendant l'arrêt du serveur, on quitte l'appli
				// avec un code erreur
				System.exit(1);
			}
		}
		
		// Fermeture du shell
		System.exit(0);
		
		return true;
	}

}
