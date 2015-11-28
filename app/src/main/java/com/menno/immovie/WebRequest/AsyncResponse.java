package com.menno.immovie.WebRequest;

import com.menno.immovie.Objects.Movie;

import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public interface AsyncResponse {
    void OnReceiveMovies(ArrayList<Movie> output);
    void OnReveiveTrailerReview(ArrayList<Movie> output);
}
