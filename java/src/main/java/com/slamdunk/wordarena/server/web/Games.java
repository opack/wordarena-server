package com.slamdunk.wordarena.server.web;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/games/{id}")
public class Games {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGame(@PathParam("id") String id) {
    	Date now = new Date();
        return 	
    		"{"
				+ "_id : " + id + ","
				+ "startTime : '" + now + "',"
				+ "type : 'DUEL',"
				+ "objective : 'CONQUEST',"
				+ "curRound : 0,"
				+ "curTurn : 0,"
				+ "curPlayer : 0,"
				+ "firstPlayer : 0,"
				+ "gameOver : false,"
				+ "players : ['alan','bob'],"
				+ "arena : {"
				+ "},"
				+ "wordsPlayed : ["
					+ "{"
						+ "time : '" + now + "',"
						+ "playerId : 'alan',"
						+ "wordId : 'TEST'"
					+ "}"
				+ "],"
				+ "lastPlay : ["
					+ "{"
						+ "x : 1,"
						+ "y : 5"
					+ "}"
				+ "],"
				+ "chats : ["
					+ "{"
						+ "time : '" + now + "',"
						+ "playerId : 'alan',"
						+ "message : 'coucou !'"
					+ "}"
				+ "]"
			+ "}";
    }
}
