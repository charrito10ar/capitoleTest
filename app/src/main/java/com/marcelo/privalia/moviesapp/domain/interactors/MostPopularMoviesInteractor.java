package com.marcelo.privalia.moviesapp.domain.interactors;

import com.marcelo.privalia.moviesapp.data.bodies.MoviesResponseBody;
import com.marcelo.privalia.moviesapp.data.bodies.SearchResponseBody;
import com.marcelo.privalia.moviesapp.data.dao.MovieDAO;
import com.marcelo.privalia.moviesapp.data.dao.MovieDAOInterface;
import com.marcelo.privalia.moviesapp.domain.mappers.MoviesMapper;
import com.marcelo.privalia.moviesapp.presentation.ui.fragments.MostPopularMoviesPresenterInterface;

import org.jetbrains.annotations.Nullable;

public class MostPopularMoviesInteractor implements MostPopularMoviesInteractorInterface{

    private MostPopularMoviesPresenterInterface presenterInterface;
    private MovieDAOInterface movieDAO;

    public MostPopularMoviesInteractor(MostPopularMoviesPresenterInterface presenterInterface){
        this.presenterInterface = presenterInterface;
        this.movieDAO = new MovieDAO();
    }

    @Override
    public void loadMostPopularMovies(int page) {
        movieDAO.getMostPopular(page, new MovieDAO.MostPopularMoviesListener() {
            @Override
            public void getMostPopularSuccessful(MoviesResponseBody moviesResponseBody) {
                MoviesMapper moviesMapper = new MoviesMapper();
                presenterInterface.getMostPopularSuccessful(moviesMapper.getMoviesDomain(moviesResponseBody.getMovieBodyList()),
                        moviesResponseBody.getTotal_pages());
            }

            @Override
            public void getMostPopularError(String message) {
                presenterInterface.getMostPopularError(message);
            }
        });
    }

    @Override
    public void searchMovies(int searchPage, @Nullable String query, final boolean isNewSearch) {
        movieDAO.searchMovie(searchPage, query, new MovieDAO.SearchMoviesListener() {
            @Override
            public void searchMovieSuccessful(SearchResponseBody searchResponseBody) {
                MoviesMapper moviesMapper = new MoviesMapper();
                if(searchResponseBody.getTotal_results() <= 0){
                    presenterInterface.showEmptyView();
                }else {
                    presenterInterface.hideEmptyView();
                    presenterInterface.searchMovieSuccessful(moviesMapper.getSearchMoviesDomain(searchResponseBody.getResults()),
                            searchResponseBody.getTotal_pages(), searchResponseBody.getTotal_results(), isNewSearch);
                }
            }

            @Override
            public void searchMovieError(String message) {
                presenterInterface.searchMovieError(message);
            }
        });
    }
}