package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ShareActionProvider;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
