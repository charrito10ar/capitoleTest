package com.marcelo.privalia.moviesapp.presentation.presenters

import com.marcelo.privalia.moviesapp.domain.interactors.MostPopularMoviesInteractor
import com.marcelo.privalia.moviesapp.domain.interactors.MostPopularMoviesInteractorInterface
import com.marcelo.privalia.moviesapp.domain.model.Movie
import com.marcelo.privalia.moviesapp.presentation.ui.fragments.MostPopularMoviesPresenterInterface

import com.marcelo.privalia.moviesapp.presentation.ui.interfaces.MostPopularMoviesView

class MostPopularMoviesPresenter(private val mostPopularMoviesView: MostPopularMoviesView): MostPopularMoviesPresenterInterface {

    private companion object {
        const val MOST_POPULAR_VIEW: Int = 1
        const val SEARCH_VIEW: Int = 2
    }
    private val interactor: MostPopularMoviesInteractorInterface = MostPopularMoviesInteractor(this)
    private var mostPopularPage: Int = 1
    private var searchPage: Int = 1
    private var endedMostPopularList: Boolean = false
    private var endedSearchList: Boolean = false
    private var activeResult: Int = MOST_POPULAR_VIEW
    private var query: String = ""

    fun loadMostPopularMovies() {
        mostPopularMoviesView.showLoading()
        activeResult = MOST_POPULAR_VIEW
        searchPage = 1
        if(!endedMostPopularList)
            interactor.loadMostPopularMovies(mostPopularPage)
    }

    override fun getMostPopularError(message: String) {
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.showError(message)
        mostPopularMoviesView.hideBottomProgressBar()
    }

    override fun getMostPopularSuccessful(movieList: MutableList<Movie>, total_pages: Int) {
        mostPopularPage++
        if(mostPopularPage >= total_pages){ endedMostPopularList = true }
        mostPopularMoviesView.loadMostPopularMoviesSuccessful(movieList)
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.hideBottomProgressBar()
    }

    fun searchMovies(newText: String?) {
        activeResult = SEARCH_VIEW
        query = newText!!
        resetList()
        if(newText!!.length > 0){
            mostPopularMoviesView.showLoading()
            interactor.searchMovies(searchPage, newText, true)
        }
    }

    override fun searchMovieError(message: String) {
        mostPopularMoviesView.showError(message)
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.hideBottomProgressBar()
    }

    override fun searchMovieSuccessful(movieList: MutableList<Movie>, total_pages: Int, total_results: Int, isNewSearch: Boolean) {
        if(searchPage >= total_pages){ endedSearchList = true }
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.hideBottomProgressBar()
        if(isNewSearch){
            searchPage = 1
        }
        mostPopularMoviesView.searchMovieSuccessful(movieList, isNewSearch)
    }

    fun loadMoreResults() {
        when(activeResult){
            MOST_POPULAR_VIEW-> loadMostPopularMovies()
            SEARCH_VIEW-> loadMoreSearchResults()
        }
    }

    private fun loadMoreSearchResults() {
        mostPopularMoviesView.showBottomProgressBar()
        searchPage++
        interactor.searchMovies(searchPage, query, false)
    }

    fun resetList(){
        searchPage = 1
        mostPopularPage = 1
        mostPopularMoviesView.resetList()
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.hideEmptyView()
        mostPopularMoviesView.hideSearchMessage()
    }

    override fun showEmptyView() {
        mostPopularMoviesView.hideLoading()
        mostPopularMoviesView.showEmptyView()
    }

    override fun hideEmptyView() {
        mostPopularMoviesView.hideEmptyView()
    }
}