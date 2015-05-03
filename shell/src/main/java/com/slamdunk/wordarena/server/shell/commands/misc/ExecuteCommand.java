package com.slamdunk.wordarena.server.shell.commands.misc;

import java.io.BufferedReader;
import java.io.IOException;

import com.slamdunk.wordarena.server.shell.commands.AbstractShellCommand;

public class ExecuteCommand extends AbstractShellCommand {

	@Override
	public boolean execute(BufferedReader in) {
		try {
			// Lecture de la commande à exécuter et des paramètres
			System.out.print("   Command         : ");
			String command = in.readLine();
			if (command == null || command.isEmpty()) {
				System.out.println("ERROR : Command cannot be empty.");
				return false;
			}
			
			System.out.print("   JSON params [{}]: ");
			String jsonParameters = in.readLine();
			if (jsonParameters == null
			|| jsonParameters.isEmpty()) {
				jsonParameters = "{}";
				System.out.println("INFO : Using empty parameters JSON object.");
			}
			
			// Envoie la commande au serveur
			return sendCommand(command.toUpperCase(), jsonParameters) != null;
			
		} catch (IOException e) {
			System.out.println("ERROR : Error while reading input : " + e.getMessage() + ". Command will not be executed.");
		}
		
		return false;
	}

}
