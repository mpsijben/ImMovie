package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.Objects.Movie;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMovieStore  extends AsyncTask<Movie, Void, Void> {

    private Activity activity;

    public FavoriteMovieStore(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        Movie movie = movies[0];

        Uri contentUri = MovieContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();

        ContentValues values = new ContentValues();
        values.put(MovieContract._ID, movie.id);
        values.put(MovieContract.NAME, movie.name);
        values.put(MovieContract.OVERVIEW, movie.overview);
        values.put(MovieContract.IMAGEURL, movie.imageUrl);
        values.put(MovieContract.RATING, movie.rating);
        values.put(MovieContract.RELEASEDATE, movie.releaseDate);
        values.put(MovieContract.IMAGEMENUURL, movie.imageMenuUrl);
        contentResolver.insert(contentUri, values);


        return null;
    }
}
