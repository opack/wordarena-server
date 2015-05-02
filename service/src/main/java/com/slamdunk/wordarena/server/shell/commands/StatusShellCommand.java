package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class StatusShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		if (server.getConfig() == null) {
			System.out.println("INFO : Server not initialized. Use init command to prepare the server.");
		} else if (!server.isListening()) {
			System.out.println("INFO : Server initialized but not listening. Use start command to listen for clients.");
		} else {
			System.out.println("INFO : Server initialized and listening on " + server.getConfig().serverAddress + ":" + server.getConfig().serverPort);
		}
		
		return true;
	}

}
