package com.slamdunk.wordarena.server.data.player;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.slamdunk.wordarena.server.data.ConnectionFactory;
import com.slamdunk.wordarena.server.data.player.PlayerDAO;
import com.slamdunk.wordarena.server.data.player.PlayerVO;

/**
 * Service en charge de manipuler les entités Player
 */
public class PlayerService {
	public List<PlayerVO> getPlayers() throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao =session.getMapper(PlayerDAO.class);
		List<PlayerVO> players= dao.getAllPlayers();
		
		session.close();
		return players;
	}
	public PlayerVO getPlayerById(long id) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao =session.getMapper(PlayerDAO.class);
		PlayerVO player =dao.getPlayerById(id);
		
		session.close();
		return player;
	}
	public int createPlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao =session.getMapper(PlayerDAO.class);
		int count = dao.doCreatePlayer(vo);
			
		session.commit();
		session.close();
		return count;
	}
	public int updatePlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao =session.getMapper(PlayerDAO.class);
		int count = dao.doUpdatePlayer(vo);
		
		session.commit();
		session.close();
		return count;
	}
	public int deletePlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao =session.getMapper(PlayerDAO.class);
		int count= dao.doDeletePlayer(vo);
		
		session.commit();
		session.close();
		return count;
	}
	public int deletePlayer(long id) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
		
		PlayerDAO dao = session.getMapper(PlayerDAO.class);
		int count = dao.doDeletePlayer(id);
		
		session.commit();
		session.close();
		return count;
	}
}
