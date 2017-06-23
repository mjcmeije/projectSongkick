package nl.avans.mm.projectsongkick.presentation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.avans.mm.projectsongkick.R;


public class MainScreenSectionsPagerAdapter extends FragmentPagerAdapter {
	
	private Context context;
	private MainScreenArtistsFragment tab1;
	private MainScreenLocationsFragment tab2;
	//================================================================================
	// Constructors
	//================================================================================
	
	public MainScreenSectionsPagerAdapter(FragmentManager fm, Context context) {
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
				tab1 = new MainScreenArtistsFragment();
				return tab1;
			
			case 1:
				tab2 = new MainScreenLocationsFragment();
				return tab2;
			
			default:
				return null;
		}
	}
	
	public Fragment getTab1() {
		return tab1;
	}
	
	public MainScreenArtistsFragment getMapFragment() {
		return tab1;
	}
	
	public MainScreenLocationsFragment getListFragment() {
		return tab2;
	}
	
	public Fragment getTab2() {
		return tab2;
	}
	
	@Override
	public int getCount() {
		return 2;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getResources().getString(R.string.homescreen_left_tab);
			case 1:
				return context.getResources().getString(R.string.homescreen_right_tab);
			default:
				break;
		}
		return null;
	}
}