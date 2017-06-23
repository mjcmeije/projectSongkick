package nl.avans.mm.projectsongkick;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;
import nl.avans.mm.projectsongkick.domain.Artist;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ArtistRequest.Listener {
	
	private final String TAG = getClass().getSimpleName();

	// Event related
//	private ListView eventsListView = null;
//	private ArrayList<Event> events = new ArrayList<>();
//	private EventAdapter eventAdapter = null;

	// Artist related
	private ListView artistListView;
	private List<Artist> artists = new ArrayList<>();
	private ArtistAdapter artistAdapter;

	private EditText searchText = null;
	private Button searchButton = null;
	private MainScreenSectionsPagerAdapter sectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sectionsPagerAdapter = new MainScreenSectionsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

		searchText = (EditText) findViewById(R.id.searchText);
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);

		artistListView = (ListView) findViewById(R.id.events_lv);
		artistAdapter = new ArtistAdapter(getApplicationContext(), getLayoutInflater(), artists);
		artistListView.setAdapter(artistAdapter);

		ViewPager viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(sectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

//		getEvents();
//		eventsListView.setOnItemClickListener(this);

	}
	
//	public void getFavoriteReports(String serviceRequestId) {
//		FavoriteReportManager.emptyArray();
//		ApiHomeScreen apiHomeScreen = new ApiHomeScreen(this, this);
//		String[] urls = new String[]{"http://37.34.59.50/breda/CitySDK/requests.format?service_request_id=" + serviceRequestId};
//		apiHomeScreen.execute(urls);
//	}
	
	@Override
	public void onClick(View v) {
		artists.clear();
		getArtist();
	}

//	public void getEvents() {
//		String str = searchText.getText().toString();
//		Log.i(TAG, "Gezocht op: " + searchText);
//		ApiEvents apiEvents = new ApiEvents(this);
//		String[] urls = new String[] {"http://api.songkick.com/api/3.0/search/artists.json?query=" + str + "&apikey=rX8RhAq6lkDw5OnK"};
//		apiEvents.execute(urls);
//	}

	public void getArtist() {
		String str = searchText.getText().toString();
		Log.i(TAG, "Gezocht op: " + searchText);
		ArtistRequest artistRequest = new ArtistRequest(this);
		String[] urls = new String[] {"http://api.songkick.com/api/3.0/search/artists.json?query=" + str + "&apikey=rX8RhAq6lkDw5OnK"};
		artistRequest.execute(urls);
	}

	//  http://api.songkick.com/api/3.0/search/artists.json?query={search_query}&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/metro_areas/31372/calendar.json?apikey=rX8RhAq6lkDw5OnK
	//	http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=Radiohead&type=festival
	//  http://api.songkick.com/api/3.0/search/locations.json?query=breda&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=" + str

	@Override
	public void onArtistAvailable(Artist artist) {
		artists.add(artist);
		Log.i(TAG, "Artists: " + artist.getName());
		artistAdapter.notifyDataSetChanged();
	}

//	@Override
//	public void onEventAvailable(Event event) {
//		// Add event(s) to array
//		events.add(event);
//		Log.i(TAG, "Event added (" + event.getDisplayName() + ")");
//		eventAdapter.notifyDataSetChanged();
//	}
	
	@Override
	public void noConnectionAvailable() {
		Toast toast = Toast.makeText(this, "No connection available.", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
