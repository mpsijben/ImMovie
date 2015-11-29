package com.menno.immovie.ContentProvider;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by Menno on 29-11-2015.
 */
public interface TrailerContract extends ProviGenBaseContract {

    @Column(Column.Type.INTEGER)
    String MOVIE_ID = ContentProvider.MOVIEID;

    @Column(Column.Type.TEXT)
    public static final String NAME = "name";

    @Column(Column.Type.TEXT)
    public static final String SOURCE = "source";

    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse(ContentProvider.AUTHORITY + "trailer");
}
