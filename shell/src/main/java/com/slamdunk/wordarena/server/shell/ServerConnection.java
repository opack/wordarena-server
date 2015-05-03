package com.slamdunk.wordarena.server.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ServerConnection {
	/**
	 * Lance un thread qui se connecte au serveur et lui envoie une commande sans paramètres
	 * @param command
	 * @param callback
	 */
	public JsonObject sendCommand(final String command) {
		return sendCommand(command, "{}");
	}
	
	/**
	 * Lance un thread qui se connecte au serveur et lui envoie une commande.
	 * @param command
	 * @param parameters
	 * @param callback
	 */
	public JsonObject sendCommand(final String command, final JsonObject parameters) {
		return sendCommand(command, parameters.toString());
	}
	
	/**
	 * Lance un thread qui se connecte au serveur et lui envoie une commande.
	 * @param command
	 * @param parameters
	 * @param callback
	 */
	public JsonObject sendCommand(String command, String parameters) {
		// Si aucun paramètre n'a été spécifié, alors on indique un objet vide
		if (parameters.isEmpty()) {
			parameters = "{}";
		}
		
		try (
			// Ouvre une connexion vers le serveur
			Socket socket = new Socket("localhost", 1601);
		){
			// Envoie la requête au serveur
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			writer.println("{\"command\":\"" + command + "\",\"parameters\":" + parameters + "}");
			writer.flush();
			
			// Récupère la réponse
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String response = reader.readLine();
			
			// Transforme la réponse en objet Json
			StringReader responseReader = new StringReader(response);
	    	JsonReader jsonReader = Json.createReader(responseReader);
	    	return jsonReader.readObject();
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR : Unsupported encoding UTF-8 : " + e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println("ERROR : Server not reachable on localhost:1601.");
		} catch (IOException e) {
			System.out.println("ERROR : I/O error while reaching to server.");
		} catch (SecurityException e) {
			System.out.println("ERROR : Error while trying to connect to server for security reasons.");
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR : Specified database server port (1601) is incorrect.");
		}
		
		return null;
	}
}
