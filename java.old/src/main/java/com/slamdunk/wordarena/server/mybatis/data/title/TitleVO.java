package com.slamdunk.wordarena.server.mybatis.data.title;

import java.io.Serializable;

public class TitleVO implements Serializable {
	private static final long serialVersionUID = -2416396729741642013L;
	
	private long id;
	private String title;
	private int nbRequiredStars;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNbRequiredStars() {
		return nbRequiredStars;
	}
	public void setNbRequiredStars(int nbRequiredStars) {
		this.nbRequiredStars = nbRequiredStars;
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
		TitleVO other = (TitleVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TitleVO [id=" + id + ", title=" + title + ", nbRequiredStars=" + nbRequiredStars + "]";
	}
}
