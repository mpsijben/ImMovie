package com.menno.immovie.ContentProvider;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by Menno on 29-11-2015.
 */
public interface ReviewContract extends ProviGenBaseContract {

    @Column(Column.Type.INTEGER)
    String MOVIE_ID = ContentProvider.MOVIEID;

    @Column(Column.Type.TEXT)
    public static final String AUTHOR = "author";

    @Column(Column.Type.TEXT)
    public static final String CONTENT = "content";

    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse(ContentProvider.AUTHORITY + "review");
}
