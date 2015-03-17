package com.slamdunk.wordarena.server.data;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {

	private static SqlSessionFactory sqlMapper;
	private static Reader reader; 

	static{
		try{
			reader	  = Resources.getResourceAsReader("data/mybatis-config.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession(){
		return sqlMapper;
	}
}
