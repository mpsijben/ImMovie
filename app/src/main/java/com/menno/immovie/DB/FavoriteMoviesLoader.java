package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.Objects.Movie;

import java.util.ArrayList;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMoviesLoader extends AsyncTask<Void, Void, ArrayList<Movie>> {

    public FavoriteResponse delegate = null;
    private Activity activity;

    public  FavoriteMoviesLoader(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected ArrayList<Movie> doInBackground(Void... arg) {

        Uri contentUri = MovieContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();
        String where = "";
        String[] args = new String[] {  };

        Cursor cursor = contentResolver.query(contentUri, null, where, args, "");
        if (cursor == null) {
            return null;
        }
        int id = cursor.getColumnIndex(MovieContract._ID);
        int name = cursor.getColumnIndex(MovieContract.NAME);
        int overview = cursor.getColumnIndex(MovieContract.OVERVIEW);
        int imageUrl = cursor.getColumnIndex(MovieContract.IMAGEURL);
        int rating = cursor.getColumnIndex(MovieContract.RATING);
        int releaseDate = cursor.getColumnIndex(MovieContract.RELEASEDATE);
        int imageMenuUrl = cursor.getColumnIndex(MovieContract.IMAGEMENUURL);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        while (cursor.moveToNext()) {
            Movie movie = new Movie(cursor.getInt(id), cursor.getString(name), cursor.getString(overview), cursor.getString(imageUrl), cursor.getInt(rating), cursor.getString(releaseDate), cursor.getString(imageMenuUrl));
            movies.add(movie);
        }

        cursor.close();
        return movies;


    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        delegate.OnFavorite(movies);
    }
}
