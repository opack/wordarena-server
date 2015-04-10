package com.slamdunk.wordarena.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class WordArenaServer {
	/**
	 * Indique si le serveur tourne et attend des connexions
	 */
	private boolean listening;
	
	/**
	 * Process de BD
	 */
	private Process dbProcess;
	
	/**
	 * La socket sur laquelle se connectent les clients
	 */
	private ServerSocket socket;
	
	/**
	 * Configuration de lancement du serveur
	 */
	private ServerConfig config;
	
	/**
	 * Client connecté à la base de données
	 */
	private MongoClient mongoClient;
	
	public boolean start() {
		listening = false;
		
		if (connectDatabase()) {
			runServer();
			return true;
		}
		return false;
	}
	
	/**
	 * Ouvre une connexion à la base de données
	 */
	private boolean connectDatabase() {
		try {
			// Démarre le serveur de BD
			dbProcess = Runtime.getRuntime().exec(config.mongoDbExec);
			System.out.println("INFO : Database started using command '" + config.mongoDbExec + "'");
			
			// Se connecte à la base
			boolean dbServerAvailable = false;
			for (int curTry = 0; curTry < 10; curTry++) {
				// Vérifie si le serveur est démarré en tentant une connexion sur l'@ et le port
				try (
					Socket dbSocket = new Socket(config.mongoDbAddress, config.mongoDbPort)
				){
					if (dbSocket.isConnected()) {
						dbServerAvailable = true;
						break;
					}
				} catch (UnknownHostException e) {
					System.out.println("ERROR : Database not reachable because the configured host (" + config.mongoDbAddress + ":" + config.mongoDbPort + ") is unknown.");
				} catch (IOException e) {
					System.out.println("ERROR : Error while reaching to database server. Trying again in 1 second...");
				} catch (SecurityException e) {
					System.out.println("ERROR : Error while trying to connect to database server for security reasons.");
				} catch (IllegalArgumentException e) {
					System.out.println("ERROR : Specified database server port (" + config.mongoDbPort + ") is incorrect.");
				}
				
				// Patiente 1s avant de réessayer
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Si l'attente est interrompue, ce n'est pas grave : on fera juste la prochaine tentative plus tôt.
				}
			}
			if (dbServerAvailable) {
				mongoClient = new MongoClient(config.mongoDbAddress, config.mongoDbPort);
				System.out.println("INFO : Database server started and connected.");
			} else {
				System.out.println("ERROR : Database unreachable. Server will not start.");
			}
			
			return dbServerAvailable;
		} catch (IOException e) {
			System.out.println("ERROR : Error while executing database server launch command.");
		}
		return false;
	}

	/**
	 * Démarre l'écoute sur l'adresse et le port indiqués dans la config
	 */
	private void runServer() {
        listening = true;
        try (
    		// Création du server, qui devra être close() en cas de problème
    		ServerSocket serverSocket = new ServerSocket(config.serverPort, 0, Inet4Address.getByName(config.serverAddress));
        ) {
        	this.socket = serverSocket;
        	System.out.println("INFO : Server started. Listening on " + config.serverAddress + ":" + config.serverPort);
        	
            while (listening) {
            	System.out.println("INFO : Waiting for a client...");
            	
            	// Attente de connexion d'un client
            	Socket socket = serverSocket.accept();
            	System.out.println("INFO : Client connected (" + socket.getRemoteSocketAddress() + "). Launching a new ClientHandler...");
            	
            	// Création d'un thread pour gérer la communication avec ce client
	            new ClientHandler(socket, mongoClient).start();
	        }
	    } catch (SocketException e) {
	    	// On n'affiche l'erreur que si listening == true. Dans le cas contraire, l'erreur
	    	// est levée suite à la brusque fermeture du socket.
	    	if (listening) {
	    		System.out.println("ERROR : Error on server socket : " + e.getMessage());
	    	}
		} catch (IOException e) {
            System.out.println("ERROR : Could not listen on port " + config.serverAddress + ":" + config.serverPort);
        }
        
        listening = false;
        System.out.println("INFO : Server stopped listening for clients.");
    }
	
	/**
	 * Arrête le serveur et ferme la connexion à la base
	 */
	public void stop() {
		System.out.println("INFO : Stopping server...");
		listening = false;
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("ERROR : Error occurred while closing socket : " + e.getMessage());
			e.printStackTrace();
		}

		if (mongoClient != null) {
			System.out.println("INFO : Closing database connexion...");
			mongoClient.close();
		}
		
		if (dbProcess.isAlive()) {
			System.out.println("INFO : Shutting database server down...");
			dbProcess.destroy();
		}
	}

	public boolean isListening() {
		return listening;
	}

	public void setConfig(ServerConfig config) {
		this.config = config;
	}

	public ServerConfig getConfig() {
		return config;
	}

	public MongoClient getDatabaseClient() {
		return mongoClient;
	}
}