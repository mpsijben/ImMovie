package com.menno.immovie.WebRequest;

import com.menno.immovie.Objects.Movie;

import java.util.ArrayList;

/**
 * Created by Menno on 29-11-2015.
 */
public interface MovieResponse {
    void OnReceiveMovies(ArrayList<Movie> output);
}