package com.menno.immovie.WebRequest;

import android.net.Uri;
import android.util.Log;

import com.menno.immovie.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Menno on 28-11-2015.
 */
public class JSON {

    private static final String LOG_TAG = JSON.class.getSimpleName();

    public static String getJSONInfo(String baseUrl, String searchOption, String sortString)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;

        try {
            final String API_PARAM = "api_key";

            Uri builtUri = Uri.parse(baseUrl).buildUpon()
                    .appendQueryParameter(API_PARAM, BuildConfig.MOVIE_API_KEY)
                    .appendQueryParameter(searchOption, sortString)
                    .build();

            URL realUrl = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) realUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return jsonStr;
    }
}
