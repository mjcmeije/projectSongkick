package nl.avans.mm.projectsongkick.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;

//public class ArtistAdapter extends BaseAdapter {
//
//	private final String TAG = getClass().getSimpleName();
//
//	private Context mContext;
//	private LayoutInflater mInflator;
//	private List artistList;
//
//	public ArtistAdapter(Context context, LayoutInflater layoutInflater, List<Artist> artists) {
//		mContext = context;
//		mInflator = layoutInflater;
//		artistList = artists;
//	}
//
//	@Override
//	public int getCount() {
//		return artistList.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		Log.i(TAG, "getItem " + position);
//		return artistList.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//
//		Log.i(TAG, "getView " + position);
//
//		ViewHolder viewHolder;
//
//		if (convertView == null) {
//
//			convertView = mInflator.inflate(R.layout.artist_listview_row, null);
//
//			viewHolder = new ViewHolder();
//			viewHolder.artistName = (TextView) convertView.findViewById(R.id.listRowTvArtistName);
//			viewHolder.artistImage = (ImageView) convertView.findViewById(R.id.listRowIvArtistImage);
//
//			convertView.setTag(viewHolder);
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
//		Artist artist = (Artist) artistList.get(position);
//		viewHolder.artistName.setText(artist.getName());
//		Picasso.with(mContext).load(artist.getArtistImageUrl()).into(viewHolder.artistImage);
//		return convertView;
//	}
//
//	private static class ViewHolder {
//		public TextView artistName;
//		public ImageView artistImage;
//	}
//}
public class ArtistAdapter extends ArrayAdapter<Artist> {

	private Artist artist;
	private Context context;
	
	public ArtistAdapter(Context context, ArrayList<Artist> artists) {
		super(context, R.layout.activity_main, artists);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertViewInitial, ViewGroup parent) {

		// Create artist
		artist = getItem(position);
		View convertView = convertViewInitial;

		// Check for existing view
		if (convertViewInitial == null) {
			convertViewInitial = LayoutInflater.from(getContext()).inflate(R.layout.artist_listview_row, parent, false);
		}

		// Select row items
//		ImageView mediaUrl = (ImageView) convertViewInitial.findViewById(R.id.fragmentListViewRow_IV_mediaUrl);
//		TextView description = (TextView) convertViewInitial.findViewById(R.id.fragmentListViewRow_TV_description);
//		TextView status = (TextView) convertViewInitial.findViewById(R.id.fragmentListViewRow_TV_status);
//		TextView category = (TextView) convertViewInitial.findViewById(R.id.fragmentListViewRow_TV_category);
		TextView artistName = (TextView) convertViewInitial.findViewById(R.id.lvRowTvArtistName);
		ImageView artistImage = (ImageView) convertViewInitial.findViewById(R.id.lvRowIvArtistImage);

		// Get and set content
		artistName.setText(artist.getName());
		Picasso.with(getContext()).load(artist.getArtistImageUrl()).into(artistImage);
		// Return view
		return convertViewInitial;
	}
}

