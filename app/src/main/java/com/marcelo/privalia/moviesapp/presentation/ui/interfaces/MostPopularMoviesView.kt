package com.marcelo.privalia.moviesapp.presentation.ui.interfaces

import com.marcelo.privalia.moviesapp.domain.model.Movie

interface MostPopularMoviesView {
    fun loadMostPopularMovies()
    fun loadMostPopularMoviesSuccessful(movieList: List<Movie>)
    fun showError(message: String)
    fun searchMovieSuccessful(movieList: MutableList<Movie>, newSearch: Boolean)
    fun openSearchView()
    fun closeSearchView()
    fun resetList()
    fun showLoading()
    fun hideLoading()
    fun showEmptyView()
    fun hideEmptyView()
    fun showSearchMessage()
    fun hideSearchMessage()
    fun showBottomProgressBar()
    fun hideBottomProgressBar()
}