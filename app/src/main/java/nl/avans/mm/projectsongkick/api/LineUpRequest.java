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

import nl.avans.mm.projectsongkick.domain.Artist;

public class LineUpRequest extends AsyncTask<String, Void, String> {

	private Listener listener = null;
	private final String TAG = getClass().getSimpleName();

	public LineUpRequest(Listener listener) {
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
			JSONObject event = results.getJSONObject("event");

			JSONArray performanceArray = event.getJSONArray("performance");

			// For each artist in artist array
			for(int idx = 0; idx < performanceArray.length(); idx++) {
				// Artist object in array
				JSONObject performanceObject = performanceArray.getJSONObject(idx);

				String displayName = performanceObject.getJSONObject("artist").getString("displayName");
				String artistId = performanceObject.getJSONObject("artist").getString("id");

				// Create new Artist
				Artist artist = new Artist();

				artist.setName(displayName);
				artist.setArtistId(artistId);

				Log.i(TAG, displayName + " " + artistId);

				// Callback
				listener.onEventPerformanceAvailable(artist);
			}
		} catch (JSONException e) {
			Log.e("ERR", e.getLocalizedMessage());
		}
	}

	public interface Listener {
		void onEventPerformanceAvailable(Artist artist);
	}
}

