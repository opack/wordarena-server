package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public interface ShellCommand {
	
	/**
	 * Exécute la commande dans le contexte (serveur et input stream) spécifié
	 * @param server
	 * @param in
	 * @return true si la commande a bien été exécutée
	 */
	boolean execute(WordArenaServer server, BufferedReader in);
}
