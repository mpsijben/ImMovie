package com.menno.immovie;

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

        if(((MainActivity)getActivity()).tableMode) {
            ((Callback) getActivity()).onItemSelected(output.get(0));
        }
    }

    public interface Callback {
        public void onItemSelected(Movie movie);

    }

    public MovieFragment() {
    }

    public void LoadMovies()
    {
        LoadMovies("popularity.desc");
    }

    public void LoadMovies(String sort)
    {
        WebRequestMoviesTask task = new WebRequestMoviesTask();
        task.delegate = this;
        task.execute(sort);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putParcelableArrayList("movies", movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")){
            LoadMovies();
        }
        else
        {
            movies = savedInstanceState.getParcelableArrayList("movies");
        }

        mMovieAdapter = new MovieAdapter(getActivity(), movies);

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                Movie movie = mMovieAdapter.getItem(position);

                ((Callback) getActivity()).onItemSelected(movie);
            }
        });
        return rootView;
    }
}
