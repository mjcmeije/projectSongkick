package nl.avans.mm.projectsongkick.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.adapter.ArtistAdapter;

public class LocationFragment extends Fragment{

	public static final String LOCATION = "LOCATION";
	private final String TAG = getClass().getSimpleName();
	private ArtistAdapter artistAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.location_fragment, container, false);

		ListView locationListView = (ListView) rootView.findViewById(R.id.location_lv);
		return rootView;
	}
}