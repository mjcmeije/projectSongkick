package nl.avans.mm.projectsongkick.businesslogic;

import java.util.ArrayList;
import java.util.List;

import nl.avans.mm.projectsongkick.domain.Event;

/**
 * Created by qsl on 28/06/2017.
 */

public class EventManager {

	private static List<Event> events = new ArrayList<>();

	public static void addEvent(Event event) {
		events.add(event);
	}

	public static List<Event> getEvent() {
		return events;
	}

	public static void emptyArray() {
		events.clear();
	}
}
