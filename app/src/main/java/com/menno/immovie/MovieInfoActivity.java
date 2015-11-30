package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.menno.immovie.Fragments.MovieFragmentInfo;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

    public MovieFragmentInfo myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieFragmentInfo.MOVIETAG, getIntent().getExtras().getParcelable(MovieFragmentInfo.MOVIETAG));
            myFragment = new MovieFragmentInfo();
            myFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentInfo,myFragment).commit();

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_item_share:
                myFragment.OnShareTrailer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
