package com.slamdunk.wordarena.server.shell.commands;

import com.slamdunk.wordarena.server.shell.commands.lexis.LexisAddCommand;
import com.slamdunk.wordarena.server.shell.commands.lexis.LexisLoadCommand;
import com.slamdunk.wordarena.server.shell.commands.lexis.LexisRemoveCommand;
import com.slamdunk.wordarena.server.shell.commands.lexis.LexisValidateCommand;
import com.slamdunk.wordarena.server.shell.commands.misc.ExecuteCommand;
import com.slamdunk.wordarena.server.shell.commands.misc.UnknownShellCommand;
import com.slamdunk.wordarena.server.shell.commands.server.ShutdownCommand;
import com.slamdunk.wordarena.server.shell.commands.server.StartCommand;
import com.slamdunk.wordarena.server.shell.commands.server.StatusCommand;
import com.slamdunk.wordarena.server.shell.commands.server.StopCommand;
import com.slamdunk.wordarena.server.shell.commands.shell.ExitCommand;
import com.slamdunk.wordarena.server.shell.commands.shell.HelpCommand;

public class CommandFactory {
	public ShellCommand create(String command) {
		switch (command) {
		case "execute":
			return new ExecuteCommand();
		case "exit":
			return new ExitCommand();
		case "help":
			return new HelpCommand();
		case "lexisLoad":
			return new LexisLoadCommand();
		case "lexisAdd":
			return new LexisAddCommand();
		case "lexisRemove":
			return new LexisRemoveCommand();
		case "lexisValidate":
			return new LexisValidateCommand();
		case "shutdown":
			return new ShutdownCommand();
		case "start":
			return new StartCommand();
		case "status":
			return new StatusCommand();
		case "stop":
			return new StopCommand();
		default:
			return new UnknownShellCommand(command);
		}
	}
}
