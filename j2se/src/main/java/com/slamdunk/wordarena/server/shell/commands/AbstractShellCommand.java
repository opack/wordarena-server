package com.slamdunk.wordarena.server.shell.commands;

import com.slamdunk.wordarena.server.WordArenaServer;

public abstract class AbstractShellCommand implements ShellCommand {
	
	protected boolean checkServerInitialized(WordArenaServer server) {
		if (server.getConfig() == null) {
			System.out.println("ERROR : Server not initialized. Use init command to prepare the server.");
			return false;
		}
		return true;
	}
	
	protected boolean checkServerListening(WordArenaServer server) {
		if (!server.isListening()) {
			System.out.println("ERROR : Server initialized but not listening. Use start command to listen for clients.");
			return false;
		}
		return true;
	}
}
