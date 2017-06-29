package nl.avans.mm.projectsongkick.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;
import nl.avans.mm.projectsongkick.api.ArtistRequest;
import nl.avans.mm.projectsongkick.businesslogic.ArtistManager;
import nl.avans.mm.projectsongkick.domain.Artist;
import nl.avans.mm.projectsongkick.presentation.ArtistActivity;
import nl.avans.mm.projectsongkick.presentation.MainActivity;

public class ArtistFragment extends Fragment implements AdapterView.OnItemClickListener, ArtistRequest.Listener {

	public static final String EXTRA_ARTIST = "ARTIST";
	private final String TAG = getClass().getSimpleName();
	private String query;
	private ArtistAdapter artistAdapter;
	private ArtistManager artistQuery;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.artist_fragment, container, false);
		
		ListView artistsListView;
		artistsListView = (ListView) rootView.findViewById(R.id.artists_lv);

		artistQuery = new ArtistManager();

		artistAdapter = new ArtistAdapter(getContext(), artistQuery.getArtist()); //fragmentListView_LV_reportsList
		artistsListView.setAdapter(artistAdapter);
		artistsListView.setOnItemClickListener(this);
//		addArtistsToList();
		return rootView;
	}

	public void getArtist() {
		artistQuery.emptyArray();
		String query = MainActivity.searchBox.getText().toString();
		Log.i(TAG, "Gezocht op: " + query);
		ArtistRequest artistRequest = new ArtistRequest(this);
		String[] urls = new String[]{"http://api.songkick.com/api/3.0/search/artists.json?query=" + query + "&apikey=rX8RhAq6lkDw5OnK"};
		artistRequest.execute(urls);
	}
	
//	public void addArtistsToList() {
//		artistAdapter.notifyDataSetChanged();
//	}

	@Override
	public void onArtistAvailable(Artist artist) {
		artistQuery.addArtist(artist);
		String ArtistID = artist.getArtistId();
		String imageUrl = "https://assets.sk-static.com/images/media/profile_images/artists/"+ArtistID+"/huge_avatar";
		artist.setArtistImageUrl(imageUrl);
		Log.i(TAG, "Artists: " + artist.getName() + " & ArtistID: " + artist.getArtistId() + ", ArtistIamgeURL: " + artist.getArtistImageUrl());
//		sectionsPagerAdapter.getListFragment().addArtistsToList();
		artistAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Artist artist = artistQuery.getArtist().get(position);
		Log.i(TAG, "Position: " + position + "Artist: " + artist.getName());
		Intent intent = new Intent(getContext(), ArtistActivity.class);
		intent.putExtra(EXTRA_ARTIST, artist);
		startActivity(intent);
	}
}