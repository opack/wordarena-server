package com.slamdunk.wordarena.server;

import com.slamdunk.wordarena.server.data.user.bo.UserBO;
import com.slamdunk.wordarena.server.data.user.vo.UserVO;

public class TestMyBatis {
	public static void main(String[] args) {
		testUser();
		testPlayer();
	}

	private static void testUser() {
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
