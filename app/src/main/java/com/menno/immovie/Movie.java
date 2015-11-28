package com.menno.immovie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class Movie  implements Parcelable {
    public int id;
    public String name;
    public String overview;
    public String imageUrl;
    public int rating;
    public String releaseDate;
    public String imageMenuUrl;

    public Movie(int id, String name, String overview, String imageUrl, int rating, String releaseDate, String imageMenuUrl)
    {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.imageMenuUrl = imageMenuUrl;
    }

    private Movie(Parcel in)
    {
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.imageUrl = in.readString();
        this.rating = in.readInt();
        this.releaseDate = in.readString();
        this.imageMenuUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.overview);
        parcel.writeString(this.imageUrl);
        parcel.writeInt(this.rating);
        parcel.writeString(this.releaseDate);
        parcel.writeString(this.imageMenuUrl);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };
}
