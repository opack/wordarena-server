package com.slamdunk.wordarena.server;

import java.io.IOException;
import java.util.Properties;

public class Launcher {
	private Server server;
	
	public Launcher() {
		server = new Server();
	}
	
	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.launch();
	}
	
	public void launch() {
		System.out.println("Launching server...");
		if (loadConfig()) {
			startServer();
		}
	}
	
	private boolean loadConfig() {
		// Ouvre le fichier de propriétés
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("server.properties"));
			
			// Récupère la configuration du serveur
			ServerConfig config = new ServerConfig();
			
			config.serverAddress = properties.getProperty("server.address");
			config.serverPort = Integer.parseInt(properties.getProperty("server.port"));
			
			config.mongoDbExec = properties.getProperty("mongodb.exec");
			config.mongoDbAddress = properties.getProperty("mongodb.address");
			config.mongoDbPort = Integer.parseInt(properties.getProperty("mongodb.port"));
			
			config.logFile = properties.getProperty("logfile");
			
			// Passe la config au serveur
			server.setConfig(config);

			System.out.println("INFO : Configuration loaded and passed to the server.");
			return true;
		} catch (IOException e) {
			System.out.println("ERROR : Error while reading server.properties : " + e.getMessage());
		}
		
		return false;
	}
	
	private boolean startServer() {
		// Le serveur ne peut pas être démarré s'il n'a pas été initialisé
		if (server.getConfig() == null) {
			System.out.println("ERROR : Server not initialized. Use init command to prepare the server.");
			return false;
		}
		
		// Lance l'attente des clients dans un thread pour ne pas bloquer le shell
		new Thread() {
			public void run() {
				if (server.start()
				&& server.isListening()) {
					// Si le serveur écoute, alors il est bien démarré
					System.out.println("INFO : Server started.");
				} else {
					System.out.println("ERROR : An error occurred while starting server. Please check log for more details.");
				}
			};
		}.start();
		
		return true;
	}
}
