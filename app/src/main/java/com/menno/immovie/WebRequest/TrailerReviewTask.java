package com.menno.immovie.WebRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.menno.immovie.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menno on 28-11-2015.
 */
public class TrailerReviewTask extends AsyncTask<ArrayList<Movie>, Void, ArrayList<Movie>> {
    public AsyncResponse delegate = null;
    private final String LOG_TAG = TrailerReviewTask.class.getSimpleName();

    private ArrayList<Movie> getMoviesFromJson(ArrayList<Movie> movies)
            throws JSONException {

        for(int i = 0; i <  movies.size(); i++) {
            String jsonTrailerReview = getTrailersAndReview(movies.get(i).id);
            JSONObject jsonObject = new JSONObject(jsonTrailerReview);
            JSONObject trailers = jsonObject.getJSONObject("trailers");
            JSONArray trailersArray = trailers.getJSONArray("youtube");

            for (int t = 0; t < trailersArray.length(); t++) {
                JSONObject trailerObject = trailersArray.getJSONObject(t);
                // Log.e("e", trailerObject.getString("source"));
            }

            JSONObject reviews = jsonObject.getJSONObject("reviews");
            JSONArray reviewsArray = reviews.getJSONArray("results");
            for (int r = 0; r < reviewsArray.length(); r++) {
                JSONObject reviewObject = reviewsArray.getJSONObject(r);
                Log.e("e", reviewObject.getString("author"));
            }
        }

        return movies;
    }

    private String getTrailersAndReview(int movieId)
    {
        final String SORT_PARAM = "append_to_response";
        final String SORT_STRING = "trailers,reviews";

        return JSON.getJSONInfo("http://api.themoviedb.org/3/movie/" + Integer.toString(movieId), SORT_PARAM, SORT_STRING);
    }

    @Override
    protected ArrayList<Movie> doInBackground(ArrayList<Movie>... movies) {

        if (movies.length == 0) return null;

        try {
            return getMoviesFromJson(movies[0]);
        } catch (JSONException e){
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

       return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.OnReveiveTrailerReview(result);
    }


}
