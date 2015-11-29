package com.menno.immovie.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Menno on 28-11-2015.
 */
public class Review implements Parcelable {

    private String author;
    private String content;

    public Review(String author, String content)
    {
        this.author = author;
        this.content = content;
    }

    private Review(Parcel in)
    {
        this.author = in.readString();
        this.content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.author);
        parcel.writeString(this.content);
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        @Override
        public Review[] newArray(int i) {
            return new Review[i];
        }

    };
}
