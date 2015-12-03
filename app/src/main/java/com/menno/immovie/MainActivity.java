package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.menno.immovie.Fragments.MovieFragment;
import com.menno.immovie.Fragments.MovieFragmentInfo;
import com.menno.immovie.Objects.Movie;

/**
 * Created by Menno Sijben on 21-11-2015.
 */

public class MainActivity extends AppCompatActivity implements MovieFragment.Callback {

    public static boolean tableMode = false;
    private static final String INFOFRAGMENT_TAG = "INFOT";

    public MovieFragment myFragment;

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
            Intent intent = new Intent(this, MovieInfoActivity.class)
                    .putExtra(MovieFragmentInfo.MOVIETAG, movie);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            myFragment = new MovieFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentcontainer, myFragment, MovieFragment.MOVIETAG)
                    .commit();
        }
        else
        {
            myFragment = (MovieFragment)getSupportFragmentManager().findFragmentByTag(MovieFragment.MOVIETAG);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.titlbar_popular);

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
            myFragment.LoadMovies();
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(R.string.titlbar_popular);
            return true;
        }
        else if(id == R.id.menuSortRating) {
            myFragment.LoadMovies("vote_average.desc");
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(R.string.titlbar_rating);
            return true;
        }
        else if(id == R.id.menuFavorite) {
            myFragment.LoadFavorites();
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(R.string.titlbar_favorite);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
