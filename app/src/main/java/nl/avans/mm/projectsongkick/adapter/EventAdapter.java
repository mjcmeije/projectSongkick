package nl.avans.mm.projectsongkick.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.domain.Event;
import nl.avans.mm.projectsongkick.R;

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

		if (convertView == null) {
			
			convertView = mInflator.inflate(R.layout.event_listview_row, null);
			
			viewHolder = new ViewHolder();
			viewHolder.displayName = (TextView) convertView.findViewById(R.id.tvEventName);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Event event = (Event) mEventArrayList.get(position);
		
		viewHolder.displayName.setText(event.getDisplayName());

		return convertView;
	}
	
	private static class ViewHolder {
		public TextView displayName;
	}
}
