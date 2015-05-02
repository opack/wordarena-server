package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.WordArenaServer;

public class InitAndStartShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		if (new InitShellCommand().execute(server, in)) {
			return new StartShellCommand().execute(server, in);
		}
		return false;
	}

}
