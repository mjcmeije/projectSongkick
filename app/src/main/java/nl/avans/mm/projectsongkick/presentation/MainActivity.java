package nl.avans.mm.projectsongkick.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
	private final String TAG = getClass().getSimpleName();
	// Event related
//	private ListView eventsListView = null;
//	private ArrayList<Event> events = new ArrayList<>();
//	private EventAdapter eventAdapter = null;
	public static final String ARTIST = "artist";

	public static EditText searchBox = null;
	private Button searchButton;
	public String query;
	private SectionsPagerAdapter sectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// No shadow under action bar
		getSupportActionBar().setElevation(0);
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

		searchBox = (EditText) findViewById(R.id.searchText);

		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);

		ViewPager viewPager = (ViewPager) findViewById(R.id.container);
		Log.i(TAG, "This page: " + viewPager.getCurrentItem());
		viewPager.setAdapter(sectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		Log.i(TAG, "Tab: " + tabLayout.getSelectedTabPosition());
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
		sectionsPagerAdapter.getArtistFragment().getArtist();
	}

//	public void getEvents() {
//		String str = searchText.getText().toString();
//		Log.i(TAG, "Gezocht op: " + searchText);
//		ApiEvents apiEvents = new ApiEvents(this);
//		String[] urls = new String[] {"http://api.songkick.com/api/3.0/search/artists.json?query=" + str + "&apikey=rX8RhAq6lkDw5OnK"};
//		apiEvents.execute(urls);
//	}

	//  http://api.songkick.com/api/3.0/search/artists.json?query={search_query}&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/metro_areas/31372/calendar.json?apikey=rX8RhAq6lkDw5OnK
	//	http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=Radiohead&type=festival
	//  http://api.songkick.com/api/3.0/search/locations.json?query=breda&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=" + str

//	@Override
//	public void onArtistAvailable(Artist artist) {
//		ArtistManager.addArtist(artist);
//		ArtistID = artist.getArtistId();
//		String imageUrl = "https://assets.sk-static.com/images/media/profile_images/artists/"+ArtistID+"/huge_avatar";
//		artist.setArtistImageUrl(imageUrl);
//		Log.i(TAG, "Artists: " + artist.getName() + " & ArtistID: " + artist.getArtistId() + ", ArtistIamgeURL: " + artist.getArtistImageUrl());
//		sectionsPagerAdapter.getListFragment().addArtistsToList();
//	}

//	@Override
//	public void onEventAvailable(Event event) {
//		// Add event(s) to array
//		events.add(event);
//		Log.i(TAG, "Event added (" + event.getDisplayName() + ")");
//		eventAdapter.notifyDataSetChanged();
//	}
}
