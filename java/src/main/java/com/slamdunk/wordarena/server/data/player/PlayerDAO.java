package com.slamdunk.wordarena.server.data.player;

import java.util.List;

/**
 * Interface implémentée par MyBatis afin de manipuler les entités
 * de Player
 */
public interface PlayerDAO {

	public List<PlayerVO> getAllPlayers() throws Exception;

	public PlayerVO getPlayerById(long id) throws Exception;

	public int doCreatePlayer(PlayerVO vo) throws Exception;

	public int doUpdatePlayer(PlayerVO vo) throws Exception; 
	
	public int doDeletePlayer(PlayerVO vo) throws Exception;  

}