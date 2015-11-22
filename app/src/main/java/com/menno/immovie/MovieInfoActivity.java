package com.menno.immovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class MovieInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    }
}
