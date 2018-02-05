package com.marcelo.privalia.moviesapp.data.dao;

public interface MovieDAOInterface {
    void getMostPopular(int page, MovieDAO.MostPopularMoviesListener mListener);
    void searchMovie(int page, String query, MovieDAO.SearchMoviesListener mListener);
}
