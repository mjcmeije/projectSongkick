package nl.avans.mm.projectsongkick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiEvents.Listener {
	
	private static final String TAG = "MainActivity";
	private ListView eventsListView = null;
	private ArrayList<Event> events = new ArrayList<Event>();
	private EventAdapter eventAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eventsListView = (ListView) findViewById(R.id.events_lv);
		
		eventAdapter = new EventAdapter(getApplicationContext(), getLayoutInflater(), events); //fragmentListView_LV_reportsList
		eventsListView.setAdapter(eventAdapter);
		getEvents();
//		eventsListView.setOnItemClickListener(this);
	}
	
//	public void getFavoriteReports(String serviceRequestId) {
//		FavoriteReportManager.emptyArray();
//		ApiHomeScreen apiHomeScreen = new ApiHomeScreen(this, this);
//		String[] urls = new String[]{"http://37.34.59.50/breda/CitySDK/requests.format?service_request_id=" + serviceRequestId};
//		apiHomeScreen.execute(urls);
//	}
	
	public void getEvents() {
//		String str = searchText.getText().toString();
		ApiEvents apiEvents = new ApiEvents(this);
		String[] urls = new String[] {"http://api.songkick.com/api/3.0/metro_areas/31372/calendar.json?apikey=rX8RhAq6lkDw5OnK"};
		apiEvents.execute(urls);
	}
	
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
