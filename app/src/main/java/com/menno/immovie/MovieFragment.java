package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */

public class MovieFragment extends Fragment implements AsyncResponse {

    private MovieAdapter mMovieAdapter;

    ArrayList<Movie> movies = new ArrayList<Movie>();

    public void OnReceiveMovies(ArrayList<Movie> output){
        Log.e("s", Integer.toString(output.size()));
        mMovieAdapter.clear();
        for(Movie movie : output) {
            mMovieAdapter.add(movie);
        }
    }

    public MovieFragment() {
        WebRequestMoviesTask task = new WebRequestMoviesTask();
        task.delegate = this;
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMovieAdapter = new MovieAdapter(getActivity(), movies);

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                Movie movie = mMovieAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), MovieInfoActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, movie.name);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
