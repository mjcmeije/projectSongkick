package nl.avans.mm.projectsongkick.domain;


import java.io.Serializable;

public class Event implements Serializable {
	
	private String displayName;
	private String type;
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
