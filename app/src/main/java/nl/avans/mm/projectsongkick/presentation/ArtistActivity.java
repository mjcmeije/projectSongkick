package nl.avans.mm.projectsongkick.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.EventAdapter;
import nl.avans.mm.projectsongkick.api.ArtistEventRequest;
import nl.avans.mm.projectsongkick.domain.Artist;
import nl.avans.mm.projectsongkick.domain.Event;

import static nl.avans.mm.projectsongkick.fragment.ArtistFragment.EXTRA_ARTIST;

public class ArtistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ArtistEventRequest.Listener {

	private final String TAG = getClass().getSimpleName();

	public static final String EXTRA_EVENT = "EVENT";

	private String ARTIST_NAME;
	private String ARTIST_ID;
	private EventAdapter eventAdapter;
	private ArrayList<Event> eventList;
	private ArtistEventRequest artistEventRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist_detail_screen);
		getSupportActionBar().setElevation(0);

		Bundle bundle = getIntent().getExtras();
		Artist artist = (Artist) bundle.getSerializable(EXTRA_ARTIST);

		ARTIST_NAME = artist.getName();
		ARTIST_ID = artist.getArtistId();
		setTitle(ARTIST_NAME);

		eventList = new ArrayList<>();

		ImageView artistImage = (ImageView) findViewById(R.id.tvDetailArtistImage);
		TextView artistCalendarTv = (TextView) findViewById(R.id.tvDetailArtistCalendar);
		artistCalendarTv.setText(R.string.artist_events);
		Picasso.with(getApplicationContext()).load(artist.getArtistImageUrl()).into(artistImage);

		ListView artistEventListView;
		artistEventListView = (ListView) findViewById(R.id.lvArtistEvent);
		eventAdapter = new EventAdapter(getApplicationContext(), getLayoutInflater(), eventList);
		artistEventListView.setAdapter(eventAdapter);
		artistEventListView.setOnItemClickListener(this);
		Log.i(TAG, "Artist name: " + ARTIST_NAME + " & Artist id: " + ARTIST_ID);
		getArtistEvent();
	}

	public void getArtistEvent() {
		artistEventRequest = new ArtistEventRequest(this);
		String[] urls = {"http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name="+ARTIST_NAME};
		artistEventRequest.execute(urls);
	}

	@Override
	public void onArtistEventAvailable(Event event) {
		eventList.add(event);
		Log.i(TAG, "Event name: " + event.getDisplayName() + ", ID: " + event.getEventId());
		eventAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Event event = eventList.get(position);
		Log.i(TAG, "Position: " + position + "Event: " + event.getDisplayName() + ", ID: "+ event.getEventId());
		Intent intent = new Intent(getApplicationContext(), EventActivity.class);
		intent.putExtra(EXTRA_EVENT, event);
		startActivity(intent);
	}
}
