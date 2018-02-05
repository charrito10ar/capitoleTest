package com.marcelo.privalia.moviesapp.domain.mappers;

import com.marcelo.privalia.moviesapp.data.bodies.MovieBody;
import com.marcelo.privalia.moviesapp.data.bodies.SearchBody;
import com.marcelo.privalia.moviesapp.data.utilities.DataConstants;
import com.marcelo.privalia.moviesapp.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesMapper {

    public List<Movie> getMoviesDomain(List<MovieBody> movieBodyList){
        List<Movie> movieDomainList = new ArrayList<>();
        for (MovieBody movieBody: movieBodyList) {
            movieDomainList.add(new Movie(movieBody.getTitle(), movieBody.getOverview(), DataConstants.IMG_END_POINT + "?i=" + movieBody.getImdb(), movieBody.getReleased()));
        }
        return movieDomainList;
    }

    public List<Movie> getSearchMoviesDomain(List<SearchBody> results) {
        List<Movie> movieDomainList = new ArrayList<>();
        for (SearchBody movieBody: results) {
            movieDomainList.add(new Movie(movieBody.getMovie().getTitle(), movieBody.getMovie().getOverview(),
                    DataConstants.IMG_END_POINT + "?i=" + movieBody.getMovie().getImdb(), movieBody.getMovie().getReleased()));
        }
        return movieDomainList;
    }
}