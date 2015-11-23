package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info2);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.myImageViewText);
        textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));

        ImageView iconView = (ImageView) findViewById(R.id.myImageView);
        if(iconView != null) {

            Picasso.with(this)
                    .load("http://image.tmdb.org/t/p/w342//3Kgu3ys6W6UZWWFty7rlTWgST63.jpg")
                    .fit()
                    .into(iconView, new Callback() {

                        @Override
                        public void onSuccess() {
                            Log.e("s", "in onCreate");

                        }

                        @Override
                        public void onError() {

                            Log.e("r", "in onCreate");
                        }
                    });
        }

        ImageView posterView = (ImageView) findViewById(R.id.posterimage);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185///7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg")
                .fit()
                .into(posterView, new Callback() {

                    @Override
                    public void onSuccess() {
                        Log.e("s", "in onCreate");

                    }

                    @Override
                    public void onError() {

                        Log.e("r", "in onCreate");
                    }
                });

    }
}
