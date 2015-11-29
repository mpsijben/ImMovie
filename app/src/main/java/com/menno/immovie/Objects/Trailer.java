package com.menno.immovie.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Menno on 28-11-2015.
 */
public class Trailer implements Parcelable {

    private String name;
    private String source;

    public Trailer(String name, String source)
    {
        this.name = name;
        this.source = source;
    }

    private Trailer(Parcel in)
    {
        this.name = in.readString();
        this.source = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.source);
    }

    public String getName() {
        return this.name;
    }

    public String getSource() {
        return this.source;
    }

    public static final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel parcel) {
            return new Trailer(parcel);
        }

        @Override
        public Trailer[] newArray(int i) {
            return new Trailer[i];
        }

    };
}
