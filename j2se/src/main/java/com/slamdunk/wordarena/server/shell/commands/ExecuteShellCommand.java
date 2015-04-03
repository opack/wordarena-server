package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;
import java.io.IOException;

import com.slamdunk.wordarena.server.WordArenaServer;
import com.slamdunk.wordarena.server.commands.CommandProcessor;

public class ExecuteShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		try {
			// Lecture de la commande à exécuter et des paramètres
			System.out.print("   Command         : ");
			String command = in.readLine();
			
			System.out.print("   JSON params [{}]: ");
			String jsonParameters = in.readLine();
			if (jsonParameters == null
			|| jsonParameters.isEmpty()) {
				jsonParameters = "{}";
			}
			
			// Construction de la chaine JSON
			String jsonCommand = String.format("{\"command\":\"%s\",\"parameters\":%s}", command, jsonParameters);
			System.out.println("Executing " + jsonCommand);
			
			CommandProcessor commandProcessor = new CommandProcessor();
			String result = commandProcessor.process(jsonCommand, server.getDatabaseClient());
			System.out.println(result);
			
			return true;
		} catch (IOException e) {
			System.out.println("ERROR : Error while reading input : " + e.getMessage() + ". Command will not be executed.");
		}
		
		return false;
	}

}
