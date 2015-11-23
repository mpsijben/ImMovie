package com.menno.immovie;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Menno Sijben on 21-11-2015.
 */


public class WebRequestMoviesTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    public AsyncResponse delegate = null;
    private final String LOG_TAG = WebRequestMoviesTask.class.getSimpleName();


    private ArrayList<Movie> getMoviesFromJson(String jsonStr)
            throws JSONException {
        final String movieList = "results";
        ArrayList<Movie> movies = new ArrayList<Movie>();

        JSONObject json = new JSONObject(jsonStr);
        JSONArray movieArray = json.getJSONArray(movieList);

        for(int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            movies.add(new Movie(movieObject.getInt("id"),movieObject.getString("title"),movieObject.getString("overview"),movieObject.getString("poster_path"),movieObject.getInt("vote_average"),movieObject.getString("release_date"),movieObject.getString("backdrop_path")));

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


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonStr = null;

        try {

            final String FORECAST_BASE_URL =
                    "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String API_PARAM = "api_key";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, sortString)
                    .appendQueryParameter(API_PARAM,BuildConfig.MOVIE_API_KEY)
                    .build();

            URL realUrl = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) realUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
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
