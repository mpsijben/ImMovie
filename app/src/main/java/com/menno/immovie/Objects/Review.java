package com.menno.immovie.Objects;

/**
 * Created by Menno on 28-11-2015.
 */
public class Review {

    private String author;
    private String content;

    public Review(String author, String content)
    {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }
}
