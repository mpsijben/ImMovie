package com.menno.immovie;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Menno Sijben on 21-11-2015.
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSortPopularity) {
            FragmentManager fm = getSupportFragmentManager();
            MovieFragment fragment = (MovieFragment)fm.findFragmentById(R.id.fragment);
            fragment.LoadMovies();
            return true;
        }
        else if(id == R.id.menuSortRating) {
            FragmentManager fm = getSupportFragmentManager();
            MovieFragment fragment = (MovieFragment)fm.findFragmentById(R.id.fragment);
            fragment.LoadMovies("vote_average.desc");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
