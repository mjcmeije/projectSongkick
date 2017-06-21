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

public class MainActivity extends AppCompatActivity implements ApiEvents.Listener, View.OnClickListener {
	
	private static final String TAG = "MainActivity";
	private ListView eventsListView = null;
	private ArrayList<Event> events = new ArrayList<Event>();
	private EventAdapter eventAdapter = null;
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
		
		eventsListView = (ListView) findViewById(R.id.events_lv);
		eventAdapter = new EventAdapter(getApplicationContext(), getLayoutInflater(), events); //fragmentListView_LV_reportsList
		eventsListView.setAdapter(eventAdapter);
		
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
		events.clear();
		getEvents();
	}
	
	public void getEvents() {
		String str = searchText.getText().toString();
		Log.i(TAG, "Gezocht op: " + searchText);
		ApiEvents apiEvents = new ApiEvents(this);
		String[] urls = new String[] {"http://api.songkick.com/api/3.0/search/artists.json?query=" + str + "&apikey=rX8RhAq6lkDw5OnK"};
		apiEvents.execute(urls);
	}
	//  http://api.songkick.com/api/3.0/search/artists.json?query={search_query}&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/metro_areas/31372/calendar.json?apikey=rX8RhAq6lkDw5OnK
	//	http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=Radiohead&type=festival
	//  http://api.songkick.com/api/3.0/search/locations.json?query=breda&apikey=rX8RhAq6lkDw5OnK
	//  http://api.songkick.com/api/3.0/events.json?apikey=rX8RhAq6lkDw5OnK&artist_name=" + str
	
	@Override
	public void onEventAvailable(Event event) {
		// Toevoegen array
		events.add(event);
		Log.i(TAG, "Event added (" + event.getDisplayName() + ")");
		eventAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void noConnectionAvailable() {
		Toast toast = Toast.makeText(this, "No connection available.", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
