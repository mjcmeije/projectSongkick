package nl.avans.mm.projectsongkick.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;
import nl.avans.mm.projectsongkick.businesslogic.ArtistManager;

public class MainScreenArtistsFragment extends Fragment implements AdapterView.OnItemClickListener {
	
	
	//================================================================================
	// Properties
	//================================================================================
	public static final String EXTRA_ARTIST = "ARTIST";
	private static final String TAG = "MainScreenArtistsFragment";
	private ArtistAdapter artistAdapter;
	
	//================================================================================
	// Accessors
	//================================================================================
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_main_screen_artists, container, false);
		
		ListView artistsListView;
		artistsListView = (ListView) rootView.findViewById(R.id.artists_lv);
		
		artistAdapter = new ArtistAdapter(getContext(), ArtistManager.getArtist()); //fragmentListView_LV_reportsList
		artistsListView.setAdapter(artistAdapter);
		artistsListView.setOnItemClickListener(this);
		
		addArtistsToList();
		return rootView;
	}
	
	public void addArtistsToList() {
		artistAdapter.notifyDataSetChanged();
	}


//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		Log.i(TAG, "Report " + position + " is geselecteerd");
//
//		Report r = ReportManager.getReports().get(position);
//		Intent detailedReportIntent = new Intent(getContext(), DetailedReportActivity.class);
//		detailedReportIntent.putExtra("MediaUrl", r.getMediaUrl());
//		detailedReportIntent.putExtra("NoImage", R.drawable.nopicturefound);
//		detailedReportIntent.putExtra(EXTRA_REPORT, r);
//		startActivity(detailedReportIntent);
//	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getContext(), ArtistActivity.class);
		intent.putExtra(EXTRA_ARTIST, ArtistManager.getArtist().get(position));
		startActivity(intent);
	}
}