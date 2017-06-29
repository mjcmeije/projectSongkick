package nl.avans.mm.projectsongkick.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;
import nl.avans.mm.projectsongkick.adapter.EventAdapter;
import nl.avans.mm.projectsongkick.adapter.StandardEventAdapter;
import nl.avans.mm.projectsongkick.api.ApiEvents;
import nl.avans.mm.projectsongkick.api.ArtistRequest;
import nl.avans.mm.projectsongkick.businesslogic.ArtistManager;
import nl.avans.mm.projectsongkick.businesslogic.EventManager;
import nl.avans.mm.projectsongkick.domain.Artist;
import nl.avans.mm.projectsongkick.domain.Event;
import nl.avans.mm.projectsongkick.domain.Location;
import nl.avans.mm.projectsongkick.presentation.MainActivity;


public class EventFragment extends Fragment implements ApiEvents.Listener {
	
	public static final String EXTRA_LOCATION = "LOCATION";
	private final String TAG = getClass().getSimpleName();
	private String query;
	private StandardEventAdapter standardEventAdapter;
	private EventManager eventQuery;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.event_fragment, container, false);
		
		ListView eventsListView;
		eventsListView = (ListView) rootView.findViewById(R.id.events_lv);
		
		eventQuery = new EventManager();
		
		standardEventAdapter = new StandardEventAdapter(getContext(), eventQuery.getEvent()); //fragmentListView_LV_reportsList
		eventsListView.setAdapter(standardEventAdapter);
//		eventsListView.setOnItemClickListener(this);
//		addArtistsToList();
		getEvent();
		return rootView;
	}
	
	public void getEvent() {
		eventQuery.emptyArray();
		Bundle extras = getActivity().getIntent().getExtras();
		Location l = (Location) extras.getSerializable(EXTRA_LOCATION);
		String ID = l.getLocationID();
		ApiEvents apiEvents = new ApiEvents(this);
		String[] urls = new String[]{"http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&location=sk:" + ID};
		apiEvents.execute(urls);
	}

//	public void addArtistsToList() {
//		artistAdapter.notifyDataSetChanged();
//	}
	
	@Override
	public void onEventAvailable(Event event) {
		eventQuery.addEvent(event);
//		String ArtistID = artist.getArtistId();
//		String imageUrl = "https://assets.sk-static.com/images/media/profile_images/artists/"+ArtistID+"/huge_avatar";
//		artist.setArtistImageUrl(imageUrl);
//		Log.i(TAG, "Artists: " + artist.getName() + " & ArtistID: " + artist.getArtistId() + ", ArtistIamgeURL: " + artist.getArtistImageUrl());
////		sectionsPagerAdapter.getListFragment().addArtistsToList();
		standardEventAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void noConnectionAvailable() {
//		Toast toast = Toast.makeText(EventFragment.this, "No connection available.", Toast.LENGTH_LONG);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.show();
	}
	
}
