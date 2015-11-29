package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.Objects.Movie;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMovieLoader extends AsyncTask<Movie, Void, Boolean> {

    public IsFavoriteResponse delegate = null;
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

        String[] mProjection =
        {
                MovieContract._ID

        };

        Cursor cursor = contentResolver.query(contentUri, mProjection, where, args, "");
        if (null == cursor) {
            return false;
        }

        if(cursor.getCount() > 0 )
        {
            isFavorite = true;
        }

        cursor.close();
        return isFavorite;
    }

    @Override
    protected void onPostExecute(Boolean isFavorite) {
        delegate.OnIsFavorite(isFavorite);
    }

}
