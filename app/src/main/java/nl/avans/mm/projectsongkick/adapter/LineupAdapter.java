package nl.avans.mm.projectsongkick.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;

/**
 * Created by qsl on 29/06/2017.
 */

public class LineupAdapter extends BaseAdapter {

	// TAG for Log.i(...)
	private static final String TAG = EventAdapter.class.getSimpleName();

	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList mEventArtistArrayList;

	public LineupAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Artist> eventArtistArrayList) {
		mContext = context;
		mInflator = layoutInflater;
		mEventArtistArrayList = eventArtistArrayList;
	}

	@Override
	public int getCount() {
		int size = mEventArtistArrayList.size();
		// Log.i("getCount()", "=" + size);
		return size;
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem " + position);
		return mEventArtistArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Log.i(TAG, "getView " + position);

		ViewHolder viewHolder;

		Artist artist = (Artist) mEventArtistArrayList.get(position);

		if (convertView == null) {

			convertView = mInflator.inflate(R.layout.artist_listview_row, null);

			viewHolder = new ViewHolder();
			viewHolder.artistName = (TextView) convertView.findViewById(R.id.lvRowTvArtistName);
			viewHolder.artistImage = (ImageView) convertView.findViewById(R.id.lvRowIvArtistImage);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.artistName.setText(artist.getName());
		Picasso.with(mContext).load(artist.getArtistImageUrl()).into(viewHolder.artistImage);

		return convertView;
	}

	private static class ViewHolder {
		public TextView artistName;
		public ImageView artistImage;
	}
}

