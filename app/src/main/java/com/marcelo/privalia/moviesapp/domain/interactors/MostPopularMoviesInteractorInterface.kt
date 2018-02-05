package com.marcelo.privalia.moviesapp.domain.interactors

interface MostPopularMoviesInteractorInterface{
    fun loadMostPopularMovies(page: Int)
    fun searchMovies(searchPage: Int, query: String , isNewSearch: Boolean)
}