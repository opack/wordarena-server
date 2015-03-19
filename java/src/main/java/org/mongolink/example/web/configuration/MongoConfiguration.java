/*
* MongoLink, Object Document Mapper for Java and MongoDB
*
* Copyright (c) 2012, Arpinum or third-party contributors as
* indicated by the @author tags
*
* MongoLink is free software: you can redistribute it and/or modify
* it under the terms of the Lesser GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* MongoLink is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* Lesser GNU General Public License for more details.
*
* You should have received a copy of the Lesser GNU General Public License
* along with MongoLink.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package org.mongolink.example.web.configuration;

import java.net.UnknownHostException;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;


public class MongoConfiguration {
	private static MongoConfiguration instance;
	
	private MongoConfiguration() {
		ContextBuilder builder = new ContextBuilder("org.mongolink.example.persistence.mapping");
		try {
			Settings settings = Settings.defaultInstance()
								.withDefaultUpdateStrategy(UpdateStrategies.DIFF)
								.withDbName("wordarena")
								.withAddresses(Lists.newArrayList(new ServerAddress("host", 7689)))
								.withAuthentication("user", "passwd");
			manager = MongoSessionManager.create(builder, settings); 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MongoConfiguration getInstance() {
		if (instance == null) {
			instance = new MongoConfiguration();
		}
		return instance;
	}
	
	private MongoSessionManager manager;
	
	public void stop() {
		manager.close();
	}

	public MongoSession createSession() {
		return manager.createSession();
	}

//    public static void stop() {
//        Singleton.INSTANCE.mongoSessionManager.close();
//    }
//
//    public static MongoSession createSession() {
//        return Singleton.INSTANCE.mongoSessionManager.createSession();
//    }
//
//    private enum Singleton {
//
//        INSTANCE;
//
//        private Singleton() {
//            ContextBuilder builder = new ContextBuilder("org.mongolink.example.persistence.mapping");
//            mongoSessionManager = create(builder, new Properties().addSettings(Settings.defaultInstance()));
//        }
//
//        private final MongoSessionManager mongoSessionManager;
//    }
}
