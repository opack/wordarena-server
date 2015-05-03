package com.slamdunk.wordarena.server.shell.commands.misc;

import java.io.BufferedReader;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class UnknownShellCommand extends AbstractShellCommand {
	private String command;
	
	public UnknownShellCommand(String command) {
		this.command = command;
	}

	@Override
	public boolean execute(BufferedReader in) {
		System.out.println("ERROR : The command '" + command + "' is unknown. Use 'help' for a list of available commands and mind the case of the commands.");
		
		return true;
	}

}
