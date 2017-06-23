package nl.avans.mm.projectsongkick.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;

public class ArtistAdapter extends BaseAdapter {

	private static final String TAG = EventAdapter.class.getSimpleName();

	private Context mContext;
	private LayoutInflater mInflator;
	private List artistList;

	public ArtistAdapter(Context context, LayoutInflater layoutInflater, List<Artist> artists) {
		mContext = context;
		mInflator = layoutInflater;
		artistList = artists;
	}

	@Override
	public int getCount() {
		int size = artistList.size();
		Log.i("getCount()", "=" + size);
		return size;
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem " + position);
		return artistList.get(position);
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

			convertView = mInflator.inflate(R.layout.artist_listview_row, null);

			viewHolder = new ViewHolder();
			viewHolder.artistName = (TextView) convertView.findViewById(R.id.listRowTvArtistName);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Artist artist = (Artist) artistList.get(position);

		viewHolder.artistName.setText(artist.getName());

		// Picasso.with(mContext).load(person.getImageUrl()).into(viewHolder.imageView);
		return convertView;
	}

	private static class ViewHolder {
		public TextView artistName;
	}
}

