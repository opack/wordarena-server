package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

public interface ShellCommand {
	
	/**
	 * Exécute la commande dans le contexte (serveur et input stream) spécifié
	 * @param in
	 * @return true si la commande a bien été exécutée
	 */
	boolean execute(BufferedReader in);
}
