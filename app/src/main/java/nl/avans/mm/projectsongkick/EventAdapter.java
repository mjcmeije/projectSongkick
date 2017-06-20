package nl.avans.mm.projectsongkick;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {
	
	// TAG for Log.i(...)
	private static final String TAG = EventAdapter.class.getSimpleName();
	
	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList mEventArrayList;
	
	public EventAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Event> eventArrayList) {
		mContext = context;
		mInflator = layoutInflater;
		mEventArrayList = eventArrayList;
	}
	
	@Override
	public int getCount() {
		int size = mEventArrayList.size();
		// Log.i("getCount()", "=" + size);
		return size;
	}
	
	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem " + position);
		return mEventArrayList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.i(TAG, "getView " + position);
		
		ViewHolder viewHolder;
		
		// Create new of gebruik een al bestaande (recycled by Android)
		// The old view to reuse, if possible. Note: You should check
		// that this view is non-null and of an appropriate type before using. If
		// it is not possible to convert this view to display the correct data,
		// this method can create a new view.
		if (convertView == null) {
			
			// Als convertView nog niet bestaat maken we een nieuwe aan.
			convertView = mInflator.inflate(R.layout.event_listview_row, null);
			
			viewHolder = new ViewHolder();
//				viewHolder.imageView = (ImageView) convertView.findViewById(R.id.productRowImageView);
//				viewHolder.title = (TextView) convertView.findViewById(R.id.productRowTitle);
//				viewHolder.specsTag = (TextView) convertView.findViewById(R.id.productRowSpecsTag);
			viewHolder.displayName = (TextView) convertView.findViewById(R.id.eventListViewRow_TV_displayName);
			
			// Koppel de view aan de viewHolder
			convertView.setTag(viewHolder);
		} else {
			// Als de convertView wel bestaat gebruiken we die.
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// En nu de viewHolder vullen met de juiste product
		Event event = (Event) mEventArrayList.get(position);
		
		viewHolder.displayName.setText(event.getDisplayName());
//			viewHolder.specsTag.setText(product.getSpecsTag());
//			viewHolder.summary.setText(product.getSummary());
//			new ImageLoader(viewHolder.imageView).execute(product.getSmallImageUrl());
		
		// Nu met caching tool - Picasso
		// Comment eerst de ImageLoader hierboven.
		// Picasso.with(mContext).load(person.getImageUrl()).into(viewHolder.imageView);
		return convertView;
	}
	
	// Holds all data to the view. Wordt evt. gerecycled door Android
	private static class ViewHolder {
		//			public ImageView imageView;
//			public TextView title;
//			public TextView specsTag;
		public TextView displayName;
	}
}
