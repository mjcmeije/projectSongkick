package nl.avans.mm.projectsongkick.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import nl.avans.mm.projectsongkick.domain.Event;

public class ApiEvents extends AsyncTask<String, Void, String> {
		
	private Listener listener = null;
	private static final String TAG = "ApiEvents";
				
		public ApiEvents(Listener listener) {
			this.listener = listener;
		}
		
		@Override
		protected String doInBackground(String... params) {
			
			BufferedReader reader = null;
			String response = "";
			
			try {
				URL url = new URL(params[0]);
				URLConnection connection = url.openConnection();
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					response += line;
				}
			} catch (MalformedURLException e) {
				Log.e("ERR", e.getLocalizedMessage());
				return null;
			} catch (IOException e) {
				Log.e("ERR", e.getLocalizedMessage());
				return null;
			} catch (Exception e) {
				Log.e("ERR", e.getLocalizedMessage());
				return null;
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						Log.e("ERR", e.getLocalizedMessage());
					}
				}
			}
			
			return response;
		}
		
		@Override
		protected void onPostExecute(String response) {
			Log.i(TAG, response);
			if ((response == null) || (response.equalsIgnoreCase(""))) {
				listener.noConnectionAvailable();
				return;
			}
			
			JSONObject jsonObject;
			try {
				// Top level json object
				jsonObject = new JSONObject(response);
				
				JSONObject test = jsonObject.getJSONObject("resultsPage");
				JSONObject test2 = test.getJSONObject("results");
				
				
				// Get all products and start looping
				JSONArray event1 = test2.getJSONArray("artist");
				for(int idx = 0; idx < event1.length(); idx++) {
					// array level objects and get product
					JSONObject thisEvent = event1.getJSONObject(idx);
					
					String displayName = thisEvent.getString("displayName");
					
					// Create new Event
					Event event = new Event();
					
					event.setDisplayName(displayName);
					

					
					// Callback
					listener.onEventAvailable(event);
				}
			} catch (JSONException e) {
				Log.e("ERR", e.getLocalizedMessage());
			}
		}
		
		public interface Listener {
			void onEventAvailable(Event event);
			
			void noConnectionAvailable();
		}
}

