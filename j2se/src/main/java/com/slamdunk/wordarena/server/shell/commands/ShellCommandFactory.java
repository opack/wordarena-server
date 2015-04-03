package com.slamdunk.wordarena.server.shell.commands;

public class ShellCommandFactory {
	public ShellCommand create(String command) {
		switch (command) {
		case "help" : return new HelpShellCommand();
		case "status" : return new StatusShellCommand();
		case "init" : return new InitShellCommand();
		case "start" : return new StartShellCommand();
		case "initAndStart" : return new InitAndStartShellCommand();
		case "execute" : return new ExecuteShellCommand();
		case "stop" : return new StopShellCommand();
		case "exit" : return new ExitShellCommand();
		default: return new UnknownShellCommand(command);
		}
	}
}
