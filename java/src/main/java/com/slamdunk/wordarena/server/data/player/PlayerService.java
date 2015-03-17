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
			List<PlayerVO> Players= dao.getAllPlayers();
		session.close();
		return Players;
	}
	public PlayerVO getPlayerById(long id) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
			PlayerDAO dao =session.getMapper(PlayerDAO.class);
			PlayerVO Player =dao.getPlayerById(id);
		session.close();
		return Player;
	}
	public PlayerVO createPlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
			PlayerDAO dao =session.getMapper(PlayerDAO.class);
			dao.doCreatePlayer(vo);
		session.commit();
		session.close();
		return vo;
	}
	public PlayerVO updatePlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
			PlayerDAO dao =session.getMapper(PlayerDAO.class);
			dao.doUpdatePlayer(vo);
		session.commit();
		session.close();
		return vo;
	}
	public int deletePlayer(PlayerVO vo) throws Exception{
		SqlSession session = ConnectionFactory.getSession().openSession();
			PlayerDAO dao =session.getMapper(PlayerDAO.class);
			int cnt= dao.doDeletePlayer(vo);
		session.commit();
		session.close();
		return cnt;
	}
}
