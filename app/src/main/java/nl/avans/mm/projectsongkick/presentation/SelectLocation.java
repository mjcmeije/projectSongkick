package nl.avans.mm.projectsongkick.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.LocationAdapter;
import nl.avans.mm.projectsongkick.api.ApiLocation;
import nl.avans.mm.projectsongkick.api.ArtistRequest;
import nl.avans.mm.projectsongkick.domain.Location;

public class SelectLocation extends AppCompatActivity implements ApiLocation.Listener, AdapterView.OnItemClickListener, View.OnClickListener{
	
	private final String TAG = getClass().getSimpleName();
	public final static String EXTRA_LOCATION = "LOCATION";
	
	public static EditText searchBox = null;
	private Button searchButton;
	public String query;
	
	private ListView listViewSelectLocation = null;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private LocationAdapter locationAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_location);
		
		// No shadow under action bar
		getSupportActionBar().setElevation(0);
				
		searchBox = (EditText) findViewById(R.id.searchText);
		
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);
		
		listViewSelectLocation = (ListView) findViewById(R.id.selectLocation_lv);
		locationAdapter = new LocationAdapter(getApplicationContext(),
				getLayoutInflater(),
				locations);
		listViewSelectLocation.setAdapter(locationAdapter);
		listViewSelectLocation.setOnItemClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		locations.clear();
		getLocations();
	}
	
	public void getLocations() {
		String query = SelectLocation.searchBox.getText().toString();
		Log.i(TAG, "Gezocht op: " + query);
		ApiLocation apiLocation = new ApiLocation(this);
		String[] urls = new String[]{"http://api.songkick.com/api/3.0/search/locations.json?query=" + query + "&apikey=rX8RhAq6lkDw5OnK"};
		apiLocation.execute(urls);
	}
	
	@Override
	public void onLocationAvailable(Location location) {
		// Toevoegen array
		locations.add(location);
		Log.i(TAG, "Location added (" + location.toString() + ")");
		locationAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i(TAG, "Item " + position + " is geselecteerd");

		Location l = locations.get(position);
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);

		intent.putExtra(EXTRA_LOCATION, l);
		startActivity(intent);
	}
}
