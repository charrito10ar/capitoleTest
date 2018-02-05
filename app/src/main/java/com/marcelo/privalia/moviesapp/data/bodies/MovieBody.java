package com.marcelo.privalia.moviesapp.data.bodies;

import com.google.gson.annotations.JsonAdapter;
import com.marcelo.privalia.moviesapp.data.bodies.adapter.MovieTypeAdapter;

@JsonAdapter(MovieTypeAdapter.class)
public class MovieBody {
    private int year;
    private String title;
    private String overview;
    private String released;
    private String imdb;

    public MovieBody() {
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
