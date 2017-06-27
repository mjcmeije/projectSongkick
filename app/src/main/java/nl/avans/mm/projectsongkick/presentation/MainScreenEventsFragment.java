package nl.avans.mm.projectsongkick.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.avans.mm.projectsongkick.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenEventsFragment extends Fragment {
	
	
	public MainScreenEventsFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_main_screen_events, container, false);
	}
	
}
