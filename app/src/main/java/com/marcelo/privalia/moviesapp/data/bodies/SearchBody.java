package com.marcelo.privalia.moviesapp.data.bodies;

public class SearchBody {
    private String type;
    private MovieBody movie;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MovieBody getMovie() {
        return movie;
    }

    public void setMovie(MovieBody movie) {
        this.movie = movie;
    }

}
