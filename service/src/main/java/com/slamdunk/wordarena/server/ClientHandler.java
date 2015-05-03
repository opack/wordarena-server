package com.slamdunk.wordarena.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.json.JsonObject;

import com.slamdunk.wordarena.server.commands.CommandProcessor;

/**
 * Gère la communication avec 1 client, de façon StateLess : une fois que la commande
 * reçue est traitée, la connexion est fermée.
 */
public class ClientHandler extends Thread {
	private Socket socket;
	private Server server;

	public ClientHandler(Socket socket, Server server) {
		super("WordArenaClientHandler");
		this.socket = socket;
		this.server = server;
	}

	public void run() {

		String logClientId = "";
		String output;
		
		try (
			// Déclaration des ressources qui seront utilisées dans le try et devront
			// être fermées à la fin (similaire à un finally)
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			final InetSocketAddress clientAddress = ((InetSocketAddress)socket.getRemoteSocketAddress());
			logClientId = clientAddress.toString();
			
			// Teste si le client est autorisé à se connecter,
			// et gère sa requête le cas échéant
        	if (server.isAllowExternalClients()
        	|| clientAddress.getAddress().isLoopbackAddress()) {
        		
        		output = acceptClient(logClientId, in);
        		
        	} else {
        		
        		output = refuseClient(logClientId);
        		
        	}
				
			// Renvoie la résponse au client
			System.out.println("INFO [" + logClientId + "] : Response sent : " + output);
			out.println(output);
			
			// Ferme la connexion
			socket.close();
		} catch (IOException e) {
			System.out.println("ERROR [" + logClientId + "] : Error while communicating through client socket.");
		}
	}
	
	private String acceptClient(String logClientId, BufferedReader in) throws IOException {
		// Récupération de la commande au format JSON
		String jsonCommand = in.readLine();
		System.out.println("INFO [" + logClientId + "] : Command received : " + jsonCommand);
		
		// Traitement de la requête
		CommandProcessor commandProcessor = new CommandProcessor();
		commandProcessor.setLogClientId(logClientId);
		String output = commandProcessor.process(jsonCommand, server);
		
		// Retour du résultat
		return output;
	}
	
	private String refuseClient(String logClientId) {
		System.out.println("INFO [" + logClientId + "] : Client refused because allowExternalClients=false.");
		
		// Prépare le message expliquant le refus de connexion
		JsonObject result = JsonResultBuilder.build(false, "External clients refused for the moment.");
		
		// Retour du résultat
		return result.toString();
	}
}