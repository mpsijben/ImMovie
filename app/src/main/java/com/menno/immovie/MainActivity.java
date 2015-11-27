package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Menno Sijben on 21-11-2015.
 */

public class MainActivity extends AppCompatActivity implements MovieFragment.Callback {

    public boolean tableMode = false;
    private static final String INFOFRAGMENT_TAG = "INFOT";

    public void onItemSelected(Movie movie)
    {
        if (tableMode){
            Bundle args = new Bundle();
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieFragmentInfo.MOVIETAG, movie);
            MovieFragmentInfo fragment = new MovieFragmentInfo();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentInfo, fragment, INFOFRAGMENT_TAG).commit();
        }
        else {
            Log.e("er", movie.name);
            Intent intent = new Intent(this, MovieInfoActivity.class)
                    .putExtra(MovieFragmentInfo.MOVIETAG, movie);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_activity_main);


        tableMode = findViewById(R.id.fragmentInfo) != null;


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
