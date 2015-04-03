package com.slamdunk.wordarena.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.mongodb.MongoClient;
import com.slamdunk.wordarena.server.commands.CommandProcessor;

/**
 * Gère la communication avec 1 client, de façon StateLess : une fois que la commande
 * reçue est traitée, la connexion est fermée.
 */
public class ClientHandler extends Thread {
	private Socket socket;
	private MongoClient mongoClient;

	public ClientHandler(Socket socket, MongoClient mongoClient) {
		super("WordArenaClientHandler");
		this.socket = socket;
		this.mongoClient = mongoClient;
	}

	public void run() {

		try (
			// Déclaration des ressources qui seront utilisées dans le try et devront
			// être fermées à la fin (similaire à un finally)
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			// Récupération de la commande au format JSON
			String jsonCommand = in.readLine();
			
			// Traitement de la requête
			CommandProcessor commandProcessor = new CommandProcessor();
			String output = commandProcessor.process(jsonCommand, mongoClient);
				
			// Renvoie la résponse au client
			out.println(output);
			
			// Ferme la connexion
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}