package com.slamdunk.wordarena.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Launcher {
	private Server server;
	
	public Launcher() {
		server = new Server();
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Please specify the configuration file path and name.");
			System.exit(1);
		}

		Launcher launcher = new Launcher();
		launcher.launch(args[0]);
	}
	
	public void launch(String configFile) {
		System.out.println("Launching server...");
		if (loadConfig(configFile)) {
			startServer();
		}
	}
	
	private boolean loadConfig(String configFile) {
		// Ouvre le fichier de propriétés
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(configFile));
			
			// Récupère la configuration du serveur
			ServerConfig config = new ServerConfig();
			
			config.serverAddress = properties.getProperty("server.address");
			config.serverPort = Integer.parseInt(properties.getProperty("server.port"));

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
			}
		}.start();
		
		return true;
	}
}
