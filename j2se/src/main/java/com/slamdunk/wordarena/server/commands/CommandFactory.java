package com.slamdunk.wordarena.server.commands;

import com.slamdunk.wordarena.server.commands.lexis.AddWordCommand;
import com.slamdunk.wordarena.server.commands.lexis.ValidateWordCommand;

public class CommandFactory {
	public static Command create(String commandName) {
		// Les noms de commande sont en majuscules
		commandName = commandName.toUpperCase();
		
		// Récupère la constante d'énumération
		if (commandName == null
		|| commandName.isEmpty()) {
			System.out.println("ERROR : Command must not be null nor empty.");
			return null;
		}
		Commands command;
		try {
			// 
			command = Commands.valueOf(commandName);
			
			// Crée le commande demandée
			switch (command) {
			case LEXIS_ADD:
				return new AddWordCommand();
			case LEXIS_VALIDATE :
				return new ValidateWordCommand();
			default:
				System.out.println("ERROR : Command '" + commandName + "' not implemented yet.");
				return null;
			}
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR : Unknown command '" + commandName + "'.");
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Commands.valueOf("znl");
	}

}
