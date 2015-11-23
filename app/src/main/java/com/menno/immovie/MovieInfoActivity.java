package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.myImageViewText);

        Bundle b = intent.getExtras();
        Movie movie = b.getParcelable(Intent.EXTRA_TEXT);
        textView.setText(movie.name);

        ImageView iconView = (ImageView) findViewById(R.id.myImageView);
        if(iconView != null) {
            Picasso.with(this).load("http://image.tmdb.org/t/p/w342" + movie.imageMenuUrl).fit().into(iconView);

        }
        ImageView posterView = (ImageView) findViewById(R.id.posterimage);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185" + movie.imageUrl).fit().into(posterView);

        TextView overviewText = (TextView) findViewById(R.id.movie_overview);
        overviewText.setText(movie.overview);

        TextView datumText = (TextView) findViewById(R.id.movie_datum);
        datumText.setText(movie.releaseDate);

        TextView scoreText = (TextView) findViewById(R.id.movie_score);
        scoreText.setText(movie.rating + "/10");

    }
}
