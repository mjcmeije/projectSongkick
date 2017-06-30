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

/**
 * Created by qsl on 28/06/2017.
 */

public class ArtistEventRequest extends AsyncTask<String, Void, String> {

	private Listener listener = null;
	private final String TAG = getClass().getSimpleName();

	public ArtistEventRequest(Listener listener) {
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
//			listener.noConnectionAvailable();
			return;
		}

		JSONObject jsonObject;
		try {
			// Top level json object
			jsonObject = new JSONObject(response);

			// Get resultsPage object
			JSONObject resultsPage = jsonObject.getJSONObject("resultsPage");

			// Get results object
			JSONObject results = resultsPage.getJSONObject("results");

			// Get artist array
			JSONArray eventArray = results.getJSONArray("event");

			// For each artist in artist array
			for(int idx = 0; idx < eventArray.length(); idx++) {
				// Artist object in array
				JSONObject eventObject = eventArray.getJSONObject(idx);

				String displayName = "";

				displayName = eventObject.getString("displayName");

				if (displayName.length() >= 45) {
					displayName = eventObject.getJSONObject("venue").getString("displayName");
				}

				String eventId = eventObject.getString("id");
				String eventStartDate = eventObject.getJSONObject("start").getString("date");

				// Create new Event
				Event event = new Event();

				event.setDisplayName(displayName);
				event.setEventId(eventId);
				event.setStartDate(eventStartDate);

				Log.i(TAG, displayName + " " + eventId + " " + eventStartDate);

				// Callback
				listener.onArtistEventAvailable(event);
			}
		} catch (JSONException e) {
			Log.e("ERR", e.getLocalizedMessage());
		}
	}

	public interface Listener {
		void onArtistEventAvailable(Event event);
	}
}
