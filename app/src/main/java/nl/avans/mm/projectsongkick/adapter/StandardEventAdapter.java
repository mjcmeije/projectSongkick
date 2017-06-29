package nl.avans.mm.projectsongkick.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;
import nl.avans.mm.projectsongkick.domain.Event;

public class StandardEventAdapter extends ArrayAdapter<Event> {
	
	private Event event;
	private Context context;
	
	public StandardEventAdapter(Context context, ArrayList<Event> events) {
		super(context, R.layout.activity_main, events);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertViewInitial, ViewGroup parent) {
		
		// Create artist
		event = getItem(position);
		View convertView = convertViewInitial;
		
		// Check for existing view
		if (convertViewInitial == null) {
			convertViewInitial = LayoutInflater.from(getContext()).inflate(R.layout.event_listview_row, parent, false);
		}
		
		// Select row items
//		ImageView mediaUrl = (ImageView) convertViewInitial.findViewById(R.id.fragmentListViewRow_IV_mediaUrl);
//		TextView description = (TextView) convertViewInitial.findViewById(R.id.fragmentListViewRow_TV_description);
//		TextView endDate = (TextView) convertViewInitial.findViewById(R.id.tvEventEndDate);
		TextView startDate = (TextView) convertViewInitial.findViewById(R.id.tvEventStartDate);
		TextView eventName = (TextView) convertViewInitial.findViewById(R.id.tvEventName);
//		ImageView artistImage = (ImageView) convertViewInitial.findViewById(R.id.lvRowIvArtistImage);
		
		// Get and set content
		eventName.setText(event.getDisplayName());
		startDate.setText("Startdatum: " + event.getStartDate());
		
//		Picasso.with(getContext()).load(artist.getArtistImageUrl()).into(artistImage);
		// Return view
		return convertViewInitial;
	}
}