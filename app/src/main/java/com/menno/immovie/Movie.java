package com.menno.immovie;

/**
 * Created by Menno Sijben on 21-11-2015.
 */
public class Movie {
    int id;
    String name;
    String overview;
    String imageUrl;
    int rating;
    String releaseDate;

    public Movie(int id, String name, String overview, String imageUrl, int rating, String releaseDate)
    {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
}
