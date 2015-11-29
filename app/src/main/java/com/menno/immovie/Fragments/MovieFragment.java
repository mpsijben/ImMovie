package com.menno.immovie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import com.menno.immovie.DB.FavoriteMoviesLoader;
import com.menno.immovie.DB.FavoriteResponse;
import com.menno.immovie.MovieAdapter;
import com.menno.immovie.Objects.Movie;
import com.menno.immovie.R;
import com.menno.immovie.WebRequest.MovieResponse;
import com.menno.immovie.WebRequest.MoviesTask;

import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */

public class MovieFragment extends Fragment implements MovieResponse, FavoriteResponse {

    private MovieAdapter mMovieAdapter;

    ArrayList<Movie> movies = new ArrayList<Movie>();

    public void OnReceiveMovies(ArrayList<Movie> output){
        mMovieAdapter.clear();
        mMovieAdapter.addAll(output);
    }

    public void OnFavorite(ArrayList<Movie> movies)
    {
        mMovieAdapter.clear();
        mMovieAdapter.addAll(movies);
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
        MoviesTask task = new MoviesTask();
        task.delegate = this;
        task.execute(sort);
    }

    public void LoadFavorites()
    {
        FavoriteMoviesLoader load = new FavoriteMoviesLoader(getActivity());
        load.delegate = this;
        load.execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
