package com.slamdunk.wordarena.server.shell.commands;

public class ShellCommandFactory {
	public ShellCommand create(String command) {
		switch (command) {
		case "execute" : return new ExecuteShellCommand();
		case "exit" : return new ExitShellCommand();
		case "help" : return new HelpShellCommand();
		case "init" : return new InitShellCommand();
		case "initAndStart" : return new InitAndStartShellCommand();
		case "reloadLexis" : return new ReloadLexisShellCommand();
		case "start" : return new StartShellCommand();
		case "status" : return new StatusShellCommand();		
		case "stop" : return new StopShellCommand();
		default: return new UnknownShellCommand(command);
		}
	}
}
