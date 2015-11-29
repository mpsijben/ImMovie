package com.menno.immovie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menno.immovie.Objects.Movie;
import com.menno.immovie.Objects.Review;
import com.menno.immovie.Objects.Trailer;
import com.menno.immovie.WebRequest.TrailerReviewResponse;
import com.menno.immovie.WebRequest.TrailerReviewTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Menno on 25-11-2015.
 */
public class MovieFragmentInfo extends Fragment implements TrailerReviewResponse {
    static String MOVIETAG = "MTAG";

    private Movie movie;
    private ArrayList<Trailer> trailers;
    private ArrayList<Review> reviews;
    private View view;

    public MovieFragmentInfo() {
    }

    public void OnReveiveTrailerReview(Movie output)
    {
        loadTrailers();
        loadReviews();
        LinearLayout one = (LinearLayout) getActivity().findViewById(R.id.review);
        one.setVisibility(View.VISIBLE);
        //if(MainActivity.tableMode) {
        //    ((Callback) getActivity()).onItemSelected(output.get(0));
        //}
    }

    public void loadTrailers()
    {

    }

    public void loadReviews()
    {

    }

    public void loadMovie()
    {
        Activity activity = getActivity();
        TextView textView = (TextView) view.findViewById(R.id.myImageViewText);
        textView.setText(movie.name);

        ImageView iconView = (ImageView) view.findViewById(R.id.myImageView);
        if(iconView != null) {
            Picasso.with(activity).load("http://image.tmdb.org/t/p/w342" + movie.imageMenuUrl).fit().into(iconView);

        }
        ImageView posterView = (ImageView) view.findViewById(R.id.posterimage);
        if(posterView != null) {
            Picasso.with(activity).load("http://image.tmdb.org/t/p/w185" + movie.imageUrl).fit().into(posterView);
        }

        TextView overviewText = (TextView) view.findViewById(R.id.movie_overview);
        overviewText.setText(movie.overview);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_info, container, false);


        if(savedInstanceState != null)
        {
            movie = savedInstanceState.getParcelable("movie");
            reviews = savedInstanceState.getParcelableArrayList("reviews");
            trailers = savedInstanceState.getParcelableArrayList("trailers");

        }
        else
        {
            Bundle arguments = getArguments();
            if (arguments == null) {
                return view;
            }

            movie = arguments.getParcelable(MOVIETAG);
            TrailerReviewTask task = new TrailerReviewTask();
            task.delegate = this;
            task.execute(movie);
        }

        loadMovie();

        if(savedInstanceState != null)
        {
            loadTrailers();
            loadReviews();
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putParcelable("movie", movie);
        outState.putParcelableArrayList("reviews", reviews);
        outState.putParcelableArrayList("trailers", trailers);
        super.onSaveInstanceState(outState);
    }


}
