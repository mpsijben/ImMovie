package com.menno.immovie.ContentProvider;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by Menno on 29-11-2015.
 */
public interface MovieContract extends ProviGenBaseContract {

    @Column(Column.Type.TEXT)
    public static final String NAME = "name";

    @Column(Column.Type.TEXT)
    public static final String OVERVIEW = "overview";

    @Column(Column.Type.TEXT)
    public static final String IMAGEURL = "imageURL";

    @Column(Column.Type.INTEGER)
    public static final String RATING = "rating";

    @Column(Column.Type.TEXT)
    public static final String RELEASEDATE = "releaseDate";

    @Column(Column.Type.TEXT)
    public static final String IMAGEMENUURL = "imageMenuUrl";

    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse(ContentProvider.AUTHORITY + "movie");
}
