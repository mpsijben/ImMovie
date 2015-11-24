package com.menno.immovie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Activity context, List<Movie> movies)
    {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }
        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_image);

        Picasso.with(getContext())
           .load("http://image.tmdb.org/t/p/w185" + movie.imageUrl)
                .fit()
           .into(iconView, new Callback() {

               @Override
               public void onSuccess() {
               }

               @Override
               public void onError() {
               }
           });

               return convertView;
           }
    }
