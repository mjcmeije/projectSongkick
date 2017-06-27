package nl.avans.mm.projectsongkick.businesslogic;


import java.util.ArrayList;

import nl.avans.mm.projectsongkick.domain.Artist;

public class ArtistManager {
	
	private static ArrayList<Artist> artists = new ArrayList<>();
	
	public static void addArtist(Artist artist) {
		artists.add(artist);
	}
	
	public static ArrayList<Artist> getArtist() {
		return artists;
	}
	
	public static void emptyArray() {
		artists.clear();
	}
}