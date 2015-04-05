package com.slamdunk.wordarena.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.mongodb.MongoClient;

public class WordArenaServer {
	/**
	 * Indique si le serveur tourne et attend des connexions
	 */
	private boolean listening;
	
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
	
	public void start() {
		connectDatabase();
		runServer();
	}
	
	/**
	 * Ouvre une connexion à la base de données
	 */
	private void connectDatabase() {
		mongoClient = new MongoClient(config.mongoDbAddress, config.mongoDbPort);
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