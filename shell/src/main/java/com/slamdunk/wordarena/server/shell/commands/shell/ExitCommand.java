package com.slamdunk.wordarena.server.shell.commands.shell;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class ExitCommand extends AbstractShellCommand {

	@Override
	public boolean execute(BufferedReader in) {
		
		// Fermeture du shell
		System.exit(0);
		
		return true;
	}

}
