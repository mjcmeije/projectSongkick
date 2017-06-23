package nl.avans.mm.projectsongkick.domain;

import java.io.Serializable;

/**
 * Created by qsl on 23/06/2017.
 */

public class Artist implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
