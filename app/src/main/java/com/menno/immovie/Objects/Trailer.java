package com.menno.immovie.Objects;

/**
 * Created by Menno on 28-11-2015.
 */
public class Trailer {

    private String name;
    private String source;

    public Trailer(String name, String source)
    {
        this.name = name;
        this.source = source;
    }

    public String getName() {
        return this.name;
    }

    public String getSource() {
        return this.source;
    }
}
