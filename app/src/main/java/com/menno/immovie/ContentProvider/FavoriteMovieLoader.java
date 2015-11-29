package com.menno.immovie.ContentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.Objects.Movie;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMovieLoader extends AsyncTask<Movie, Void, Boolean> {

    private Activity activity;

    public  FavoriteMovieLoader(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        Movie movie = movies[0];
        boolean isFavorite = false;
        Uri contentUri = MovieContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();
        String where = "_ID=?";
        String[] args = new String[] { Integer.toString(movie.id) };

        Cursor cursor = contentResolver.query(contentUri, null, where, args, "");
        if (null == cursor) {
            return false;
        }

        if(cursor.getCount() > 1 )
        {
            isFavorite = true;
        }

        cursor.close();
        return isFavorite;
    }

    @Override
    protected void onPostExecute(Boolean isFavorite) {

    }

}
