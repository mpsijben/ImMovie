package com.menno.immovie.ContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.tjeannin.provigen.ProviGenOpenHelper;
import com.tjeannin.provigen.ProviGenProvider;

/**
 * Created by Menno on 29-11-2015.
 */
public class ContentProvider extends ProviGenProvider {

    public static final String MOVIEID = "movieID";

    public static final String AUTHORITY = "content://com.menno.immovie/";

    private static Class[] contracts = new Class[]{MovieContract.class, ReviewContract.class, TrailerContract.class};

    @Override
    public SQLiteOpenHelper openHelper(Context context) {
        return new ProviGenOpenHelper(getContext(), "movie", null, 1, contracts);
    }

    @Override
    public Class[] contractClasses() {
        return contracts;
    }
}
