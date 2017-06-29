package nl.avans.mm.projectsongkick.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.avans.mm.projectsongkick.R;

public class MyEventsFragment extends Fragment{

	private final String TAG = getClass().getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.location_fragment, container, false);

//		ListView locationListView = (ListView) rootView.findViewById(R.id.location_lv);
		Log.i(TAG, "Test");
		return rootView;
	}
}