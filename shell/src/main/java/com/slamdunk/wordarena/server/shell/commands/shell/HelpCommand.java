package com.slamdunk.wordarena.server.shell.commands.shell;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class HelpCommand extends AbstractShellCommand {

	@Override
	public boolean execute(BufferedReader in) {
		// Construit le message d'aide
		StringBuilder help = new StringBuilder();
		help.append("Here is a list of available commands :\n");
		help.append("\n");
		help.append("Shell\n");
		help.append("-----\n");
		help.append(" - help          : This help. Lists all available commands.\n");
		help.append(" - exit          : Closes this shell.\n");
		help.append("\n");
		help.append("Manage server execution\n");
		help.append("-----------------------\n");
		help.append(" - shutdown      : Shuts the server down, which closes the DB connexion and stops waiting for clients.\n");
		help.append(" - status        : Prints the status of the server.\n");
		help.append(" - start         : Allow external clients to connect to the server.\n");
		help.append(" - stop          : Forbids external clients to connect to the server (typically during maintenance).\n");
		help.append("\n");
		help.append("Execute commands on server\n");
		help.append("--------------------------\n");
		help.append(" - execute       : Executes the specified command on the server.\n");
		help.append("Manages lexises\n");
		help.append("--------------------------\n");
		help.append(" - lexisLoad     : Clears and reloads the default lexis file for the specified language.\n");
		help.append(" - lexisAdd      : Adds a word to a lexis.\n");
		help.append(" - lexisRemove   : Removes a word from a lexis.\n");
		help.append(" - lexisValidate : Validates a word in a lexis.\n");
		
		// Affiche l'aide
		System.out.println(help.toString());
		
		return true;
	}

}
