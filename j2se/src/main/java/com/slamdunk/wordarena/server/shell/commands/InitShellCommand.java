package com.slamdunk.wordarena.server.shell.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import com.slamdunk.wordarena.server.WordArenaServer;
import com.slamdunk.wordarena.server.shell.ServerConfig;

public class InitShellCommand extends AbstractShellCommand {

	@Override
	public boolean execute(WordArenaServer server, BufferedReader in) {
		// Ouvre le fichier de propriétés
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("server.properties"));
			
			// Récupère la configuration du serveur
			ServerConfig config = new ServerConfig();
			
			config.serverAddress = properties.getProperty("server.address");
			config.serverPort = Integer.parseInt(properties.getProperty("server.port"));
			
			config.mongoDbAddress = properties.getProperty("mongodb.address");
			config.mongoDbPort = Integer.parseInt(properties.getProperty("mongodb.port"));
			
			config.logFile = properties.getProperty("logfile");
			
			// Crée le serveur
			server.setConfig(config);

			return true;
		} catch (IOException e) {
			System.out.println("ERROR : Error while reading server.properties : " + e.getMessage());
		}
		
		return false;
	}

}
