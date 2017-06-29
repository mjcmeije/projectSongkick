package nl.avans.mm.projectsongkick.domain;

import java.io.Serializable;



public class Location implements Serializable {
	
	private String locationName;
	private String locationID;
	private String locationCountryName;
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getLocationID() {
		return locationID;
	}
	
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	
	public String getLocationCountryName() {
		return locationCountryName;
	}
	
	public void setLocationCountryName(String locationCountryName) {
		this.locationCountryName = locationCountryName;
	}
}
