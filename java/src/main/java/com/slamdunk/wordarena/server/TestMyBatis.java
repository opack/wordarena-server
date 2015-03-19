package com.slamdunk.wordarena.server;

import com.slamdunk.wordarena.server.mybatis.data.player.PlayerService;
import com.slamdunk.wordarena.server.mybatis.data.player.PlayerVO;
import com.slamdunk.wordarena.server.mybatis.data.title.TitleVO;
import com.slamdunk.wordarena.server.mybatis.data.user.bo.UserBO;
import com.slamdunk.wordarena.server.mybatis.data.user.vo.UserVO;

public class TestMyBatis {
	public static void main(String[] args) throws Exception {
//		testUser();
		testPlayer();
	}

	private static void testPlayer() throws Exception {
		PlayerService service = new PlayerService();
		
		System.out.println("getPlayers   :" + service.getPlayers());
		
		PlayerVO p1 = new PlayerVO();
		p1.setPseudo("DBG1");
		p1.setScore(1);
		TitleVO t1 = new TitleVO();
		t1.setId(2);
		p1.setTitle(t1);
		System.out.println("createPlayer1:" + service.createPlayer(p1));
		
		PlayerVO p2 = new PlayerVO();
		p2.setPseudo("DBG2");
		p2.setScore(2);
		TitleVO t2 = new TitleVO();
		t2.setId(3);
		p2.setTitle(t2);
		System.out.println("createPlayer2:" + service.createPlayer(p2));
		
		System.out.println("getPlayers   :" + service.getPlayers());
		
		System.out.println("getPlayer1   :" + service.getPlayerById(1));
		
		p1.setId(1);
		p1.setScore(11);
		System.out.println("updatePlayer1:" + service.updatePlayer(p1));
		
		System.out.println("deletePlayer2:" + service.deletePlayer(2));
		
		System.out.println("getPlayers   :" + service.getPlayers());
		
		System.out.println("deletePlayer1:" + service.deletePlayer(p1));
		
		System.out.println("getPlayers   :" + service.getPlayers());
	}

	private static void testUser() throws Exception {
		UserBO bo = new UserBO();
		UserVO vo= new UserVO();

		vo.setAddress("Test");
		vo.setEmail("test@gmail.com");
		vo.setFullName("Full Name");
		vo.setMobile("12411515");

		System.out.println(bo.createUser(vo));
	 	System.out.println(bo.getUsers());

		vo= bo.getUserById(1);
		vo.setAddress("Test Updated11 Address");
		vo.setEmail("testupdated@gmail.com");
		vo.setFullName("Full Name Test");
		vo.setMobile("1241151511");
		bo.updateUser(vo);

		vo=bo.getUserById(1);

		System.out.println(vo);

		bo.deleteUser(vo);
	}
}
