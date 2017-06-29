package nl.avans.mm.projectsongkick.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.fragment.ArtistFragment;
import nl.avans.mm.projectsongkick.fragment.EventFragment;
import nl.avans.mm.projectsongkick.fragment.LocationFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	private Context context;
	private EventFragment tab1;
	private ArtistFragment tab2;
	private LocationFragment tab3;

	//================================================================================
	// Constructors
	//================================================================================
	
	public SectionsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}
	
	//================================================================================
	// Accessors
	//================================================================================

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				tab1 = new EventFragment();
				return tab1;
			
			case 1:
				tab2 = new ArtistFragment();
				return tab2;
			
			case 2:
				tab3 = new LocationFragment();
				return tab3;
			
			default:
				return null;
		}
	}
	
	public EventFragment getEventFragment() {
		return tab1;
	}
	
	public ArtistFragment getArtistFragment() {
		return tab2;
	}
	
	public LocationFragment getMapFragment() {
		return tab3;
	}
	
	public Fragment getTab1() {
		return tab1;
	}

	public Fragment getTab2() {
		return tab2;
	}
	
	public Fragment getTab3() {
		return tab3;
	}
	
	@Override
	public int getCount() {
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getResources().getString(R.string.homescreen_events_tab);
			case 1:
				return context.getResources().getString(R.string.homescreen_artist_tab);
			case 2:
				return context.getResources().getString(R.string.homescreen_location_tab);
			default:
				break;
		}
		return null;
	}
}