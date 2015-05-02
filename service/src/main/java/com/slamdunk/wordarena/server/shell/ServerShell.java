package com.slamdunk.wordarena.server.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.slamdunk.wordarena.server.WordArenaServer;
import com.slamdunk.wordarena.server.shell.commands.ShellCommand;
import com.slamdunk.wordarena.server.shell.commands.ShellCommandFactory;

/**
 * Classe chargée de démarrer et d'arrêter le serveur
 */
public class ServerShell {
	private WordArenaServer server;
	
	public static void main(String[] args) throws IOException {
		ServerShell shell = new ServerShell();
		shell.run();
	}
	
	public ServerShell() {
		server = new WordArenaServer();
	}
	
	/**
	 * Ecoute les commandes
	 * @throws IOException 
	 */
	public void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String command;
		ShellCommandFactory factory = new ShellCommandFactory();
		
		// Affiche un message de bienvenue
		printHello();
		
		while ((command = in.readLine()) != null) {
			// Crée la commande
			ShellCommand shellCommand = factory.create(command);
			
			// Exécute la cmomande
			shellCommand.execute(server, in);
			
			// Prompt pour la prochaine commande
			printPrompt();
		}
	}

	private void printHello() {
		System.out.println("Welcome to WordArena Server shell !");
		System.out.println("Use 'help' command for a list of available commands.");
		printPrompt();
	}
	
	private void printPrompt() {
		System.out.print("\n> ");
	}
	
}
