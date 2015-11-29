package com.menno.immovie.DB;

import com.menno.immovie.Objects.Movie;

import java.util.ArrayList;

/**
 * Created by Menno on 29-11-2015.
 */
public interface FavoriteResponse {
    void OnFavorite(ArrayList<Movie> movies);
}
