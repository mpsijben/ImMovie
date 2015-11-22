package com.menno.immovie;

import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public interface AsyncResponse {
    void OnReceiveMovies(ArrayList<Movie> output);
}
