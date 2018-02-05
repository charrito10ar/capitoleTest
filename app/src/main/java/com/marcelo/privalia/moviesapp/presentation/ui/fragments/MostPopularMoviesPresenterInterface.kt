package com.marcelo.privalia.moviesapp.presentation.ui.fragments

import com.marcelo.privalia.moviesapp.domain.model.Movie


interface MostPopularMoviesPresenterInterface {
    fun getMostPopularError(message: String)
    fun getMostPopularSuccessful(movieList: MutableList<Movie>, total_pages: Int)

    fun searchMovieError(message: String)
    fun searchMovieSuccessful(movieList: MutableList<Movie>, total_pages: Int, total_results: Int, isNewSearch: Boolean)

    fun showEmptyView()
    fun hideEmptyView()
}