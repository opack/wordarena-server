package com.slamdunk.wordarena.server.commands;

import java.util.HashMap;
import java.util.Map;

import com.slamdunk.wordarena.server.commands.lexis.AddWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.RemoveWordExecutor;
import com.slamdunk.wordarena.server.commands.lexis.ValidateWordExecutor;

public class CommandFactory {
	private static Map<String, Class<? extends CommandExecutor>> commands;
	
	static {
		commands = new HashMap<String, Class<? extends CommandExecutor>>();
		CommandFactory.register(Commands.LEXIS_ADD.name(), AddWordExecutor.class);
		CommandFactory.register(Commands.LEXIS_REMOVE.name(), RemoveWordExecutor.class);
		CommandFactory.register(Commands.LEXIS_VALIDATE.name(), ValidateWordExecutor.class);
	}
	
	public static void register(String commandName, Class<? extends CommandExecutor> commandClass) {
		commands.put(commandName, commandClass);
	}
	
	public static CommandExecutor create(String commandName) {
		if (commandName == null
		|| commandName.isEmpty()) {
			System.out.println("ERROR : Command must not be null nor empty.");
			return null;
		}

		// Les noms de commande sont en majuscules
		commandName = commandName.toUpperCase();
		
		// Récupère la classe de l'exécuteur
		Class<? extends CommandExecutor> executorClass = commands.get(commandName);
		
		if (executorClass == null) {
			System.out.println("ERROR : Unknown command '" + commandName + "'.");
			return null;
		}
		
		try {
			// Crée le commande demandée
			return executorClass.newInstance();
			
		} catch (InstantiationException e) {
			System.out.println("ERROR : Cannot instantiate command '" + commandName + "'. (cause : " + e.getMessage() + ").");
		} catch (IllegalAccessException e) {
			System.out.println("ERROR : Cannot access to constructor for command '" + commandName + "'. (cause : " + e.getMessage() + ").");
		}
		return null;
	}
	
}
