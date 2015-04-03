package com.slamdunk.wordarena.server.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.slamdunk.wordarena.server.WordArenaServer;
import com.slamdunk.wordarena.server.commands.CommandProcessor;

/**
 * Classe chargée de démarrer et d'arrêter le serveur
 */
public class ServerShell {
	private WordArenaServer server;
	
	public static void main(String[] args) throws IOException {
		ServerShell shell = new ServerShell();
		shell.run();
	}
	
	/**
	 * Ecoute les commandes
	 * @throws IOException 
	 */
	public void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String command;
		
		printHello();
		
		while ((command = in.readLine()) != null) {
			switch (command) {
			case "help" : executeHelp(); break;
			case "status" : executeStatus(); break;
			case "init" : executeInit(); break;
			case "start" : executeStart(); break;
			case "initAndStart" : executeInitAndStart(); break;
			case "execute" : executeExecute(in); break;
			case "stop" : executeStop(); break;
			case "exit" : executeExit(); break;
			default: executeUnknownCommand(command);
			}
			
			printPrompt();
		}
	}

	private void executeExecute(BufferedReader in) {
		if (!checkServerInitialized()
		|| !checkServerListening()) {
			return;
		}
		
		try {
			// Lecture de la commande à exécuter et des paramètres
			System.out.print("\tCommand         : ");
			String command = in.readLine();
			
			System.out.print("\tJSON params [{}]: ");
			String jsonParameters = in.readLine();
			if (jsonParameters == null
			|| jsonParameters.isEmpty()) {
				jsonParameters = "{}";
			}
			
			// Construction de la chaine JSON
			String jsonCommand = String.format("{command:'%s',parameters:%s}", command, jsonParameters);
			System.out.println("Executing " + jsonCommand);
			
			CommandProcessor commandProcessor = new CommandProcessor();
			commandProcessor.process(jsonCommand);
			
		} catch (IOException e) {
			System.err.println("Error while reading input : " + e.getMessage() + ". Command will not be executed.");
		}
	}

	private void executeExit() {
		if (server != null
		&& server.isListening()) {
			executeStop();
		}
		System.exit(0);
	}

	private void executeInitAndStart() {
		executeInit();
		executeStart();
	}

	private void executeUnknownCommand(String command) {
		System.err.println("The command '" + command + "' is unknown. Use 'help' for a list of available commands and mind the case of the commands.");
	}

	private void printHello() {
		System.out.println("Welcome to WordArena Server shell !");
		System.out.println("Use 'help' command for a list of available commands.");
		printPrompt();
	}
	
	private void printPrompt() {
		System.out.print("\n> ");
	}
	
	private void executeHelp() {
		System.out.println("Available commands are :");
		System.out.println("help\tThis help. Lists all available commands.");
		System.out.println("status\tPrints the status of the server.");
		System.out.println("init\tLoads the config and creates the server, but does not start it.");
		System.out.println("start\tStarts the server for listen on configured address and port for clients.");
		System.out.println("stop\tStops the server, which closes the DB connexion and stops waiting for clients.");
		System.out.println("initAndStart\tPerforms 'init' and 'start' commands.");
		System.out.println("execute\tExecutes the specified command on the server.");
		System.out.println("exit\tStops the server (if running) and then closes this shell.");
	}

	private void executeStatus() {
		if (server != null) {
			if (server.isListening()) {
				System.out.println("Server initialized and listening on " + server.getConfig().serverAddress + ":" + server.getConfig().serverPort);
			} else {
				System.out.println("Server initialized but not listening. Use start command to listen for clients.");
			}
		} else {
			System.out.println("Server not initialized. Use init command to prepare the server.");
		}
	}
	
	private void executeStop() {
		if (!checkServerInitialized()
		|| !checkServerListening()) {
			return;
		}
		server.stop();
		
		System.out.println("Server stop command issued. Check log for more details.");
	}

	private void executeStart() {
		if (!checkServerInitialized()) {
			return;
		}
		
		// Démarre le serveur dans un thread à part pour que son attente active
		// de clients ne bloque pas le thread du shell
		new Thread() {
			public void run() {
				server.start();
			};
		}.start();
		
		System.out.println("Server start command issued. Check log for more details.");
	}
	
	private void executeInit() {
		// Ouvre le fichier de propriétés
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("server.properties"));
		} catch (IOException e) {
			System.err.println("Error while reading server.properties : " + e.getMessage());
		}
		
		// Récupère la configuration du serveur
		ServerConfig config = new ServerConfig();
		config.serverAddress = properties.getProperty("server.address");
		config.serverPort = Integer.parseInt(properties.getProperty("server.port"));
		config.mongoDbAddress = properties.getProperty("mongodb.address");
		config.mongoDbPort = Integer.parseInt(properties.getProperty("mongodb.port"));
		config.logFile = properties.getProperty("logfile");
		
		// Crée le serveur
		server = new WordArenaServer(config);
	}
	
	private boolean checkServerInitialized() {
		if (server == null) {
			System.err.println("Server not initialized. Use init command to prepare the server.");
			return false;
		}
		return true;
	}
	
	private boolean checkServerListening() {
		if (!server.isListening()) {
			System.err.println("Server initialized but not listening. Use start command to listen for clients.");
			return false;
		}
		return true;
	}
}
