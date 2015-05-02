package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class HelpShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		// Construit le message d'aide
		StringBuilder help = new StringBuilder();
		help.append("Here is a list of available commands :\n");
		help.append("\n");
		help.append("Shell\n");
		help.append("-----\n");
		help.append(" - help         : This help. Lists all available commands.\n");
		help.append(" - exit         : Stops the server (if running) and then closes this shell.\n");
		help.append("\n");
		help.append("Manage server execution\n");
		help.append("-----------------------\n");
		help.append(" - status       : Prints the status of the server.\n");
		help.append(" - init         : Loads the config and pass it to the server, but does not start it.\n");
		help.append(" - start        : Starts the server for listen on configured address and port for clients.\n");
		help.append(" - initAndStart : Performs 'init' and 'start' commands.\n");
		help.append(" - stop         : Stops the server, which closes the DB connexion and stops waiting for clients.\n");
		help.append("\n");
		help.append("Execute commands on server\n");
		help.append("--------------------------\n");
		help.append(" - execute      : Executes the specified command on the server.\n");
		help.append(" - reloadLexis  : Clears and reloads the default lexis file for the specified language.\n");
		
		// Affiche l'aide
		System.out.println(help.toString());
		
		return true;
	}

}
