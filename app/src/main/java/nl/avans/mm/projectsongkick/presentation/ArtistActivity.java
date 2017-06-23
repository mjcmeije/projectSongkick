package nl.avans.mm.projectsongkick.presentation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import nl.avans.mm.projectsongkick.R;
import nl.avans.mm.projectsongkick.domain.Artist;

import static nl.avans.mm.projectsongkick.MainActivity.ARTIST;

/**
 * Created by qsl on 23/06/2017.
 */

public class ArtistActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_artist_screen);

		Bundle bundle = getIntent().getExtras();
		Artist artist = (Artist) bundle.getSerializable(ARTIST);

		TextView artistTv = (TextView) findViewById(R.id.tvDetailArtistName);
		artistTv.setText(artist.getName());
	}
}
