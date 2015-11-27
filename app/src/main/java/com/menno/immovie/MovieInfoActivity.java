package com.menno.immovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieFragmentInfo.MOVIETAG, getIntent().getExtras().getParcelable(MovieFragmentInfo.MOVIETAG));
            MovieFragmentInfo fragment = new MovieFragmentInfo();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentInfo,fragment).commit();

        }


    }
}
