package com.slamdunk.wordarena.server;

import org.mongolink.MongoSession;
import org.mongolink.example.domain.Repositories;
import org.mongolink.example.persistence.MongoRepositories;
import org.mongolink.example.web.configuration.MongoConfiguration;

public class TestMongoLink {
	public static void main(String[] args) {
		final MongoSession session = MongoConfiguration.getInstance().createSession();
		session.start();
		Repositories.initialise(new MongoRepositories(session));
		
		// TODO Faire des commandes mongoDB
		
		session.stop();
		MongoConfiguration.getInstance().stop();
	}
}
