package com.slamdunk.wordarena.server.data.player;

import java.io.Serializable;

public class PlayerVO implements Serializable {

	private static final long serialVersionUID = 1402370983670448581L;
	
	private int id;
	private String pseudo;
	private int titleId;	// TODO A REMPLACER PAR LA VRAIE RECUP DU TITRE !!!
	private int score;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
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
		result = prime * result + id;
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
		return "PlayerVO [id=" + id + ", pseudo=" + pseudo + ", title=" + titleId
				+ ", score=" + score + "]";
	}
}
