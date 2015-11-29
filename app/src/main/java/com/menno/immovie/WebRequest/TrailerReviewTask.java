package com.menno.immovie.WebRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.menno.immovie.Objects.Movie;
import com.menno.immovie.Objects.Review;
import com.menno.immovie.Objects.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menno on 28-11-2015.
 */
public class TrailerReviewTask extends AsyncTask<Movie, Void, Movie> {
    public TrailerReviewResponse delegate = null;
    private final String LOG_TAG = TrailerReviewTask.class.getSimpleName();

    private Movie getMoviesFromJson(Movie movie)
            throws JSONException {

        String jsonTrailerReview = getTrailersAndReview(movie.id);
        JSONObject jsonObject = new JSONObject(jsonTrailerReview);
        JSONObject trailerObjects = jsonObject.getJSONObject("trailers");
        JSONArray trailersArray = trailerObjects.getJSONArray("youtube");

        ArrayList<Trailer> trailers = new ArrayList<Trailer>();
        for (int t = 0; t < trailersArray.length(); t++) {
            JSONObject trailerObject = trailersArray.getJSONObject(t);
            trailers.add(new Trailer(trailerObject.getString("name"),trailerObject.getString("source")));
        }

        JSONObject reviewObjects = jsonObject.getJSONObject("reviews");
        JSONArray reviewsArray = reviewObjects.getJSONArray("results");

        ArrayList<Review> reviews = new ArrayList<Review>();
        for (int r = 0; r < reviewsArray.length(); r++) {
            JSONObject reviewObject = reviewsArray.getJSONObject(r);
            reviews.add(new Review(reviewObject.getString("author"), reviewObject.getString("content")));
        }

        movie.trailers = trailers;
        movie.reviews = reviews;

        return movie;
    }

    private String getTrailersAndReview(int movieId)
    {
        final String SORT_PARAM = "append_to_response";
        final String SORT_STRING = "trailers,reviews";

        return JSON.getJSONInfo("http://api.themoviedb.org/3/movie/" + Integer.toString(movieId), SORT_PARAM, SORT_STRING);
    }

    @Override
    protected Movie doInBackground(Movie... movie) {

        if (movie.length == 0) return null;

        try {
            return getMoviesFromJson(movie[0]);
        } catch (JSONException e){
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

       return null;
    }

    @Override
    protected void onPostExecute(Movie result) {
        delegate.OnReveiveTrailerReview(result);
    }


}
