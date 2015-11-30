package com.menno.immovie.DB;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.menno.immovie.ContentProvider.MovieContract;
import com.menno.immovie.ContentProvider.ReviewContract;
import com.menno.immovie.ContentProvider.TrailerContract;
import com.menno.immovie.Objects.Movie;
import com.menno.immovie.Objects.Review;
import com.menno.immovie.Objects.Trailer;

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


        Uri trailerUri = TrailerContract.CONTENT_URI;
        for(int i =0; i<movie.trailers.size(); i++) {
            Trailer trailer = movie.trailers.get(i);
            ContentValues trailerValues = new ContentValues();
            trailerValues.put(TrailerContract.MOVIE_ID, movie.id);
            trailerValues.put(TrailerContract.NAME, trailer.getName());
            trailerValues.put(TrailerContract.SOURCE, trailer.getSource());
            contentResolver.insert(trailerUri, trailerValues);
        }

        Uri reviewUri = ReviewContract.CONTENT_URI;
        for(int i =0; i<movie.reviews.size(); i++) {
            Review review = movie.reviews.get(i);
            ContentValues reviewValues = new ContentValues();
            reviewValues.put(ReviewContract.MOVIE_ID, movie.id);
            reviewValues.put(ReviewContract.AUTHOR, review.getAuthor());
            reviewValues.put(ReviewContract.CONTENT, review.getContent());
            contentResolver.insert(reviewUri, reviewValues);
        }

        return null;
    }
}
