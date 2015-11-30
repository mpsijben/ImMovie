package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.ContentProvider.ContentProvider;
import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.ContentProvider.TrailerContract;
import com.menno.immovie.Objects.Movie;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMovieDelete extends AsyncTask<Movie, Void, Void> {

    private Activity activity;

    public FavoriteMovieDelete(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        Movie movie = movies[0];

        Uri contentUri = MovieContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();
        String where = "_ID=?";
        String[] args = new String[] { Integer.toString(movie.id) };
        contentResolver.delete(contentUri, where, args);

        Uri TrailerUri = TrailerContract.CONTENT_URI;
        String trailerwhere = ContentProvider.MOVIEID + "=?";
        String[] trailerargs = new String[] { Integer.toString(movie.id) };
        contentResolver.delete(TrailerUri, trailerwhere,  trailerargs);

        return null;
    }
}
