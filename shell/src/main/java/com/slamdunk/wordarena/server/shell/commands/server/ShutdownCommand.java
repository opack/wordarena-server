package com.slamdunk.wordarena.server.shell.commands.server;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class ShutdownCommand extends AbstractShellCommand {
	private final static String COMMAND_NAME = "SERVER_SHUTDOWN";

	@Override
	public boolean execute(BufferedReader in) {
		return sendCommand(COMMAND_NAME) != null;
	}

}
