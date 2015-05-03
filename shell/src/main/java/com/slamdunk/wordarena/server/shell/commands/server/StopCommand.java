package com.slamdunk.wordarena.server.shell.commands.server;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class StopCommand extends AbstractShellCommand {
	private final static String COMMAND_NAME = "SERVER_STOP";

	@Override
	public boolean execute(BufferedReader in) {
		return sendCommand(COMMAND_NAME) != null;
	}

}
