package com.slamdunk.wordarena.server.mybatis.data.blessing;

import java.io.Serializable;

public class BlessingVO implements Serializable {
	private static final long serialVersionUID = 4735133922513520376L;

	private long id;
	private String blessing;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBlessing() {
		return blessing;
	}
	public void setBlessing(String blessing) {
		this.blessing = blessing;
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
		BlessingVO other = (BlessingVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BlessingVO [id=" + id + ", blessing=" + blessing + "]";
	}
}
