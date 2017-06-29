package nl.avans.mm.projectsongkick.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Location;

public class LocationAdapter extends BaseAdapter {
	
	// TAG for Log.i(...)
	private static final String TAG = LocationAdapter.class.getSimpleName();
	
	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList mLocationArrayList;
	
	public LocationAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Location> locationArrayList)
	{
		mContext = context;
		mInflator = layoutInflater;
		mLocationArrayList = locationArrayList;
	}
	
	@Override
	public int getCount() {
		int size = mLocationArrayList.size();
		// Log.i("getCount()", "=" + size);
		return size;
	}
	
	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem " + position);
		return mLocationArrayList.get(position);
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
		if(convertView == null) {
			
			// Als convertView nog niet bestaat maken we een nieuwe aan.
			convertView = mInflator.inflate(R.layout.activity_select_lcation_listview_row, null);
			
			viewHolder = new ViewHolder();
//			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.productRowImageView);
//			viewHolder.title = (TextView) convertView.findViewById(R.id.productRowTitle);
//			viewHolder.specsTag = (TextView) convertView.findViewById(R.id.productRowSpecsTag);
			viewHolder.cityName = (TextView) convertView.findViewById(R.id.cityName_tv);
//			viewHolder.countryName = (TextView) convertView.findViewById(R.id.countryName_tv);
			
			// Koppel de view aan de viewHolder
			convertView.setTag(viewHolder);
		} else {
			// Als de convertView wel bestaat gebruiken we die.
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// En nu de viewHolder vullen met de juiste product
		Location location = (Location) mLocationArrayList.get(position);
		
		viewHolder.cityName.setText(location.getLocationName() + ", " +(location.getLocationCountryName()) + ", " +
		location.getLocationID());
//		viewHolder.countryName.setText(location.getLocationCountryName());
//		viewHolder.summary.setText(product.getSummary());
//		new ImageLoader(viewHolder.imageView).execute(product.getSmallImageUrl());
		
		// Nu met caching tool - Picasso
		// Comment eerst de ImageLoader hierboven.
		// Picasso.with(mContext).load(person.getImageUrl()).into(viewHolder.imageView);
		return convertView;
	}
	
	// Holds all data to the view. Wordt evt. gerecycled door Android
	private static class ViewHolder {
		public ImageView imageView;
		public TextView cityName;
		public TextView countryName;
		public TextView summary;
	}
	
	
	/**
	 * Interne asynchrone class om afbeeldingen te laden.
	 */
	private class ImageLoader extends AsyncTask<String, Void, Bitmap> {
		ImageView imageView;
		
		public ImageLoader(ImageView imageView) {
			this.imageView = imageView;
		}
		
		protected Bitmap doInBackground(String... urls) {
			String imageURL = urls[0];
			Bitmap bitmap = null;
			try {
				InputStream in = new java.net.URL(imageURL).openStream();
				bitmap = BitmapFactory.decodeStream(in);
				
			} catch (Exception e) {
				Log.e("Error Message", e.getMessage());
				e.printStackTrace();
			}
			return bitmap;
		}
		
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
		}
	}
	
}