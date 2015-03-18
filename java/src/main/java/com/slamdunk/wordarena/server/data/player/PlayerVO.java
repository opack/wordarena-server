package com.slamdunk.wordarena.server.data.player;

import java.io.Serializable;

import com.slamdunk.wordarena.server.data.title.TitleVO;

public class PlayerVO implements Serializable {
	private static final long serialVersionUID = 1402370983670448581L;
	
	private long id;
	private String pseudo;
	private TitleVO title;
	private int score;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public TitleVO getTitle() {
		return title;
	}
	public void setTitle(TitleVO title) {
		this.title= title;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerVO other = (PlayerVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlayerVO [id=" + id + ", pseudo=" + pseudo + ", title=" + title
				+ ", score=" + score + "]";
	}
}
