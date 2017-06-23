package nl.avans.mm.projectsongkick.domain;

import java.io.Serializable;

/**
 * Created by qsl on 23/06/2017.
 */

public class Artist implements Serializable {

	private String name;
	private String artistId;
	private String artistImageUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getArtistImageUrl() {
		return artistImageUrl;
	}

	public void setArtistImageUrl(String artistImageUrl) {
		this.artistImageUrl = artistImageUrl;
	}
}
