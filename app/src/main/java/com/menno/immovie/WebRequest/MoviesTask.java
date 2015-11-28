package com.menno.immovie.WebRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.menno.immovie.Objects.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */


public class MoviesTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    public AsyncResponse delegate = null;
    private final String LOG_TAG = MoviesTask.class.getSimpleName();


    private ArrayList<Movie> getMoviesFromJson(String jsonStr)
            throws JSONException {
        final String movieList = "results";
        ArrayList<Movie> movies = new ArrayList<Movie>();

        JSONObject json = new JSONObject(jsonStr);
        JSONArray movieArray = json.getJSONArray(movieList);

        for(int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            movies.add(new Movie(movieObject.getInt("id"), movieObject.getString("title"), movieObject.getString("overview"), movieObject.getString("poster_path"), movieObject.getInt("vote_average"), movieObject.getString("release_date"), movieObject.getString("backdrop_path")));
        }

        return movies;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... sort) {
        // If there's no url + sort method.
        String sortString = "popularity.desc";
        if (sort.length > 0) {
            sortString = sort[0];
        }
        final String SORT_PARAM = "sort_by";

        String jsonStr = JSON.getJSONInfo("http://api.themoviedb.org/3/discover/movie?", SORT_PARAM, sortString);

        try {
            return getMoviesFromJson(jsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.OnReceiveMovies(result);
    }
}
