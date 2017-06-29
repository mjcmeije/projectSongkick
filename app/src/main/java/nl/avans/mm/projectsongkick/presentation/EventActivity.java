package nl.avans.mm.projectsongkick.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;
import nl.avans.mm.projectsongkick.adapter.LineupAdapter;
import nl.avans.mm.projectsongkick.api.LineUpRequest;
import nl.avans.mm.projectsongkick.businesslogic.ArtistManager;
import nl.avans.mm.projectsongkick.domain.Artist;
import nl.avans.mm.projectsongkick.domain.Event;

import static nl.avans.mm.projectsongkick.fragment.ArtistFragment.EXTRA_ARTIST;
import static nl.avans.mm.projectsongkick.presentation.ArtistActivity.EXTRA_EVENT;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, LineUpRequest.Listener {

	private final String TAG = getClass().getSimpleName();
	private String EVENT_NAME;
	private String EVENT_ID;
	private String ARTIST_ID;
	private String ARTIST_IMAGE;
	private LineupAdapter lineupAdapter;
	private ArrayList<Artist> eventArtist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail_screen);
		getSupportActionBar().setElevation(0);

		Bundle bundle = getIntent().getExtras();
		Event event = (Event) bundle.getSerializable(EXTRA_EVENT);

		EVENT_NAME = event.getDisplayName();
		EVENT_ID = event.getEventId();

		eventArtist = new ArrayList<>();

		Log.i(TAG, "Event: " + EVENT_NAME + "ID: " + EVENT_ID);

		TextView eventName = (TextView) findViewById(R.id.tvDetailEventName);
		ListView eventPerformance = (ListView) findViewById(R.id.lvEventPerformance);


		lineupAdapter = new LineupAdapter(getApplicationContext(), getLayoutInflater(), eventArtist);
		eventPerformance.setAdapter(lineupAdapter);
		eventPerformance.setOnItemClickListener(this);
		eventName.setText(EVENT_NAME);
		getEventArtist();
	}

	public void getEventArtist() {
		LineUpRequest lineUpRequest = new LineUpRequest(this);
		String[] urls = new String[]{"http://api.songkick.com/api/3.0/events/"+EVENT_ID+".json?apikey=rX8RhAq6lkDw5OnK"};
		lineUpRequest.execute(urls);
	}


	@Override
	public void onEventPerformanceAvailable(Artist artist) {
		eventArtist.add(artist);
		ARTIST_ID = artist.getArtistId();
		ARTIST_IMAGE = "https://assets.sk-static.com/images/media/profile_images/artists/"+ARTIST_ID+"/huge_avatar";
		artist.setArtistImageUrl(ARTIST_IMAGE);
		Log.i(TAG, "Artists: " + artist.getName() + " & ArtistID: " + ARTIST_ID + ", ArtistIamgeURL: " + ARTIST_IMAGE);
		lineupAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Artist artist = eventArtist.get(position);
		Log.i(TAG, "Artist: " + artist.getName() + " "+ artist.getArtistId());
		Intent intent = new Intent(getApplicationContext(), ArtistActivity.class);
		intent.putExtra(EXTRA_ARTIST, artist);
		startActivity(intent);
	}
}
