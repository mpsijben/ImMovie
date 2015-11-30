package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.menno.immovie.ContentProvider.ContentProvider;
import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.ContentProvider.ReviewContract;
import com.menno.immovie.ContentProvider.TrailerContract;
import com.menno.immovie.Objects.Movie;
import com.menno.immovie.Objects.Review;
import com.menno.immovie.Objects.Trailer;

import java.util.ArrayList;

/**
 * Created by Menno on 29-11-2015.
 */
public class FavoriteMoviesLoader extends AsyncTask<Void, Void, ArrayList<Movie>> {

    public FavoriteResponse delegate = null;
    private Activity activity;


    private ArrayList<Trailer> GetTrailers(String ID)
    {
        Uri trailerUri = TrailerContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();

        String[] trailerArgs = new String[] { ID };
        Cursor trailerCursor = contentResolver.query( trailerUri, null, ContentProvider.MOVIEID + "=?", trailerArgs, "");


        int name = trailerCursor.getColumnIndex(TrailerContract.NAME);
        int source = trailerCursor.getColumnIndex(TrailerContract.SOURCE);
        ArrayList<Trailer> trailers = new ArrayList<Trailer>();
        while (trailerCursor.moveToNext()) {
            Trailer trailer = new Trailer(trailerCursor.getString(name), trailerCursor.getString(source));
            trailers.add(trailer);
        }
        trailerCursor.close();

        return trailers;
    }

    private ArrayList<Review> GetReviews(String ID)
    {
        Uri reviewUri = ReviewContract.CONTENT_URI;
        ContentResolver contentResolver = activity.getContentResolver();

        String[] reviewArgs = new String[] { ID };
        Cursor reviewCursor = contentResolver.query( reviewUri, null, ContentProvider.MOVIEID + "=?", reviewArgs, "");


        int author = reviewCursor.getColumnIndex(ReviewContract.AUTHOR);
        int content = reviewCursor.getColumnIndex(ReviewContract.CONTENT);
        ArrayList<Review> reviews = new ArrayList<Review>();
        while (reviewCursor.moveToNext()) {
            Review review = new Review(reviewCursor.getString(author), reviewCursor.getString(content));
            reviews.add(review);
        }
        reviewCursor.close();

        return reviews;
    }

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
            Log.e("er", Integer.toString(GetTrailers(Integer.toString(movie.id)).size()));
            movie.trailers = GetTrailers(Integer.toString(movie.id));
            movie.reviews = GetReviews(Integer.toString(movie.id));
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
