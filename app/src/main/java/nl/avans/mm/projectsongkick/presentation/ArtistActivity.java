package nl.avans.mm.projectsongkick.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;

import static nl.avans.mm.projectsongkick.presentation.MainActivity.ARTIST;

public class ArtistActivity extends AppCompatActivity {

	private final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_artist_screen);
		getSupportActionBar().setElevation(0);

		Bundle bundle = getIntent().getExtras();
		Artist artist = (Artist) bundle.getSerializable(ARTIST);

		TextView artistTv = (TextView) findViewById(R.id.tvDetailArtistName);
		ImageView artistImage = (ImageView) findViewById(R.id.tvDetailArtistImage);
		artistTv.setText(artist.getName());
		Picasso.with(getApplicationContext()).load(artist.getArtistImageUrl()).into(artistImage);

		Log.i(TAG, artist.getName() + " " + artist.getArtistId());
	}
}
