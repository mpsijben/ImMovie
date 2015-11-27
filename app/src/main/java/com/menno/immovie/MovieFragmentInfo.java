package com.menno.immovie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Menno on 25-11-2015.
 */
public class MovieFragmentInfo extends Fragment
{
    static String MOVIETAG = "MTAG";

    public MovieFragmentInfo() {
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
            textView.setText(movie.name);

            Activity activity = getActivity();
             ImageView iconView = (ImageView) rootView.findViewById(R.id.myImageView);
             if(iconView != null) {
                 Picasso.with(activity).load("http://image.tmdb.org/t/p/w342" + movie.imageMenuUrl).fit().into(iconView);

             }
             ImageView posterView = (ImageView) rootView.findViewById(R.id.posterimage);
             Picasso.with(activity).load("http://image.tmdb.org/t/p/w185" + movie.imageUrl).fit().into(posterView);

              TextView overviewText = (TextView) rootView.findViewById(R.id.movie_overview);
             overviewText.setText(movie.overview);

             TextView datumText = (TextView) rootView.findViewById(R.id.movie_datum);
             datumText.setText(movie.releaseDate);

             TextView scoreText = (TextView) rootView.findViewById(R.id.movie_score);
             scoreText.setText(movie.rating + "/10");


        }
       //


        return rootView;
    }


}
