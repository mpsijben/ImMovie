package com.menno.immovie.Fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.menno.immovie.DB.FavoriteMovieDelete;
import com.menno.immovie.DB.FavoriteMovieLoader;
import com.menno.immovie.DB.FavoriteMovieStore;
import com.menno.immovie.DB.IsFavoriteResponse;
import com.menno.immovie.Objects.Movie;
import com.menno.immovie.Objects.Review;
import com.menno.immovie.Objects.Trailer;
import com.menno.immovie.R;
import com.menno.immovie.WebRequest.TrailerReviewResponse;
import com.menno.immovie.WebRequest.TrailerReviewTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Menno on 25-11-2015.
 */
public class MovieFragmentInfo extends Fragment implements TrailerReviewResponse, IsFavoriteResponse {
    public static String MOVIETAG = "MTAG";

    private Movie movie;
    private ArrayList<Trailer> trailers;
    private ArrayList<Review> reviews;
    private View view;

    public MovieFragmentInfo() {
    }

    public void OnIsFavorite(Boolean bool) {

        Button button = (Button) view.findViewById(R.id.favorite);


        if (bool) {
            button.setText("UNFAVORITE");
        } else {
            button.setText("Favorite MOVIE");
        }
        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((Button) v).getText().toString().equals("Favorite MOVIE")) {
                    Log.e("ereree","ere");
                    ((Button) v).setText("UNFAVORITE");
                    new FavoriteMovieStore(getActivity()).execute(movie);
                } else {
                    Log.e("ereree",((Button) v).getText().toString());
                    ((Button) v).setText("Favorite MOVIE");
                    new FavoriteMovieDelete(getActivity()).execute(movie);
                }
            }
        });
    }

    public void OnReveiveTrailerReview(Movie output) {
        trailers = output.trailers;
        reviews = output.reviews;
        loadTrailers();
        loadReviews();

        //if(MainActivity.tableMode) {
        //    ((Callback) getActivity()).onItemSelected(output.get(0));
        //}
    }

    public void loadTrailers() {
        for (int i = 0; i < trailers.size(); i++) {
            Trailer trailer = trailers.get(i);

            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.trailersInfo);
            AddDataToViewButton(linearLayout, trailer.getName(), trailer.getSource());
        }

        if (trailers.size() == 0) {
            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.trailersInfo);
            AddDataToView(linearLayout, "No trailers", "");
        }


        LinearLayout one = (LinearLayout) getActivity().findViewById(R.id.trailers);
        one.setVisibility(View.VISIBLE);
    }

    public void loadReviews() {
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);

            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.reviewInfo);
            AddDataToView(linearLayout, review.getAuthor() + ":", review.getContent());
        }

        if (reviews.size() == 0) {
            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.reviewInfo);
            AddDataToView(linearLayout, "No reviews", "");
        }

        LinearLayout one = (LinearLayout) getActivity().findViewById(R.id.review);
        one.setVisibility(View.VISIBLE);
    }

    private void AddDataToView(LinearLayout linearLayout, String first, String second) {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        //ll.setpa
        int paddingPixel = 8;
        float density = getActivity().getResources().getDisplayMetrics().density;
        int paddingDp = (int) (paddingPixel * density);
        ll.setPadding(0, paddingDp, 0, 0);
        WindowManager.LayoutParams lParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        ll.setLayoutParams(lParams);

        TextView valueTV = new TextView(getActivity());
        valueTV.setText(first);
        valueTV.setLayoutParams(new TableLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 2.5f));

        TextView value2 = new TextView(getActivity());
        value2.setText(second);
        value2.setLayoutParams(new TableLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 1f));

        ll.addView(valueTV);
        ll.addView(value2);
        linearLayout.addView(ll);
    }

    private void AddDataToViewButton(LinearLayout linearLayout, String first, final String second) {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        //ll.setpa
        int paddingPixel = 8;
        float density = getActivity().getResources().getDisplayMetrics().density;
        int paddingDp = (int) (paddingPixel * density);
        ll.setPadding(0, paddingDp, 0, 0);
        WindowManager.LayoutParams lParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        ll.setLayoutParams(lParams);

        TextView valueTV = new TextView(getActivity());
        valueTV.setText(first);
        valueTV.setLayoutParams(new TableLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 2.5f));

        Button value2 = new Button(getActivity());
        value2.setText("Watch");
        value2.setLayoutParams(new TableLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 1f));
        value2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                watchYoutubeVideo(second);
            }
        });
        ll.addView(valueTV);
        ll.addView(value2);
        linearLayout.addView(ll);
    }


    public void watchYoutubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
        }
    }

    public void loadMovie() {
        Activity activity = getActivity();
        TextView textView = (TextView) view.findViewById(R.id.myImageViewText);
        textView.setText(movie.name);

        ImageView iconView = (ImageView) view.findViewById(R.id.myImageView);
        if (iconView != null) {
            Picasso.with(activity).load("http://image.tmdb.org/t/p/w342" + movie.imageMenuUrl).fit().error(R.drawable.noposteravailable).into(iconView);

        }
        ImageView posterView = (ImageView) view.findViewById(R.id.posterimage);
        if (posterView != null) {
            Picasso.with(activity).load("http://image.tmdb.org/t/p/w185" + movie.imageUrl).fit().error(R.drawable.noposteravailable).into(posterView);
        }

        TextView ratingText = (TextView) view.findViewById(R.id.rating);
        ratingText.setText("Rating: " + Integer.toString(movie.rating) + "/10");

        TextView dateText = (TextView) view.findViewById(R.id.date);
        dateText.setText("Date: " + movie.releaseDate);

        TextView overviewText = (TextView) view.findViewById(R.id.movie_overview);
        overviewText.setText(movie.overview);

    }

    public void OnShareTrailer()
    {
        if(movie != null && trailers.size() > 0) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

            share.putExtra(Intent.EXTRA_SUBJECT, movie.name + " trailer:");
            share.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + trailers.get(0).getSource());

            startActivity(Intent.createChooser(share, "Share link!"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_info, container, false);


        if (savedInstanceState != null && savedInstanceState.containsKey("movie")) {
            movie = savedInstanceState.getParcelable("movie");
            reviews = savedInstanceState.getParcelableArrayList("reviews");
            trailers = savedInstanceState.getParcelableArrayList("trailers");

        } else {
            Bundle arguments = getArguments();
            if (arguments == null) {
                return view;
            }

            movie = arguments.getParcelable(MOVIETAG);
            if(movie == null) return view;

            if(movie.trailers.size() == 0  && movie.reviews.size() == 0) {
                TrailerReviewTask task = new TrailerReviewTask();
                task.delegate = this;
                task.execute(movie);
            }

            FavoriteMovieLoader load = new FavoriteMovieLoader(getActivity());
            load.delegate = this;
            load.execute(movie);
        }

        loadMovie();

        if (savedInstanceState != null) {
            loadTrailers();
            loadReviews();
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", movie);
        outState.putParcelableArrayList("reviews", reviews);
        outState.putParcelableArrayList("trailers", trailers);
        super.onSaveInstanceState(outState);
    }


}
