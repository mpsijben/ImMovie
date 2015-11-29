package com.menno.immovie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menno.immovie.Objects.Movie;
import com.menno.immovie.WebRequest.TrailerReviewResponse;
import com.menno.immovie.WebRequest.TrailerReviewTask;
import com.squareup.picasso.Picasso;

/**
 * Created by Menno on 25-11-2015.
 */
public class MovieFragmentInfo extends Fragment implements TrailerReviewResponse {
    static String MOVIETAG = "MTAG";

    public MovieFragmentInfo() {
    }

    public void OnReveiveTrailerReview(Movie output)
    {
        output.DidCheckTrailersAndReview = true;
        LinearLayout one = (LinearLayout) getActivity().findViewById(R.id.review);
        one.setVisibility(View.VISIBLE);
        //if(MainActivity.tableMode) {
        //    ((Callback) getActivity()).onItemSelected(output.get(0));
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_info, container, false);



        TextView textView = (TextView) rootView.findViewById(R.id.myImageViewText);


       // textView.setText("test");
       // TextView textView = (TextView) rootView.findViewById(R.id.myImageViewText);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Movie movie = arguments.getParcelable(MOVIETAG);

            if(!movie.DidCheckTrailersAndReview) {
                TrailerReviewTask task = new TrailerReviewTask();
                task.delegate = this;
                task.execute(movie);
            }
            else
            {
                Log.e("h", "h");
                OnReveiveTrailerReview(movie);
            }

            textView.setText(movie.name);

            Activity activity = getActivity();
             ImageView iconView = (ImageView) rootView.findViewById(R.id.myImageView);
             if(iconView != null) {
                 Picasso.with(activity).load("http://image.tmdb.org/t/p/w342" + movie.imageMenuUrl).fit().into(iconView);

             }
             ImageView posterView = (ImageView) rootView.findViewById(R.id.posterimage);
            if(posterView != null) {
                Picasso.with(activity).load("http://image.tmdb.org/t/p/w185" + movie.imageUrl).fit().into(posterView);
            }

              TextView overviewText = (TextView) rootView.findViewById(R.id.movie_overview);
             overviewText.setText(movie.overview);

           //  TextView datumText = (TextView) rootView.findViewById(R.id.movie_datum);
           //  datumText.setText(movie.releaseDate);

           //  TextView scoreText = (TextView) rootView.findViewById(R.id.movie_score);
            // scoreText.setText(movie.rating + "/10");


        }
       //


        return rootView;
    }


}
