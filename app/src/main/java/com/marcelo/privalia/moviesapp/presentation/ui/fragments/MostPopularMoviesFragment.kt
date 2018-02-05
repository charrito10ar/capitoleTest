package com.marcelo.privalia.moviesapp.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.marcelo.privalia.moviesapp.R
import com.marcelo.privalia.moviesapp.domain.model.Movie
import com.marcelo.privalia.moviesapp.presentation.presenters.MostPopularMoviesPresenter
import com.marcelo.privalia.moviesapp.presentation.ui.adapters.MostPopularMoviesAdapter
import com.marcelo.privalia.moviesapp.presentation.ui.interfaces.MostPopularMoviesView
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.*
import com.marcelo.privalia.moviesapp.presentation.ui.adapters.InfiniteScrollListener

class MostPopularMoviesFragment : Fragment(), MostPopularMoviesView, SearchView.OnQueryTextListener {

    private var mListener: OnMostPopularFragmentListener? = null
    private lateinit var presenter: MostPopularMoviesPresenter
    private lateinit var moviesAdapter: MostPopularMoviesAdapter
    private lateinit var infiniteScrollListener :InfiniteScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MostPopularMoviesPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_most_popular_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        moviesAdapter = MostPopularMoviesAdapter()
        val mLayoutManager = LinearLayoutManager(context)
        recycler_view_most_popular_movies.layoutManager = mLayoutManager
        recycler_view_most_popular_movies.adapter = moviesAdapter
        infiniteScrollListener = InfiniteScrollListener({
            presenter.loadMoreResults()
        }, mLayoutManager)
        recycler_view_most_popular_movies.addOnScrollListener(infiniteScrollListener)
        loadMostPopularMovies()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMostPopularFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnMostPopularFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
        MenuItemCompat.setOnActionExpandListener(searchItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                openSearchView()
                return true
            }
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                closeSearchView()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    interface OnMostPopularFragmentListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance(): MostPopularMoviesFragment {
            return MostPopularMoviesFragment()
        }
    }

    override fun loadMostPopularMovies() {
        presenter.loadMostPopularMovies()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun loadMostPopularMoviesSuccessful(movieList: List<Movie>) {
        moviesAdapter.addAll(movieList)
        moviesAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {return false}

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.searchMovies(newText)
        return false
    }

    override fun searchMovieSuccessful(movieList: MutableList<Movie>, newSearch: Boolean) {
        if(newSearch){ moviesAdapter.clear()}
        moviesAdapter.addAll(movieList)
        moviesAdapter.notifyDataSetChanged()
        text_view_search_movie_hint.visibility = View.GONE
    }

    override fun closeSearchView() {
        presenter.resetList()
        loadMostPopularMovies()
        hideSearchMessage()
    }

    override fun openSearchView() {
        presenter.resetList()
        moviesAdapter.notifyDataSetChanged()
        showSearchMessage()
    }

    override fun resetList() {
        moviesAdapter.clear()
        infiniteScrollListener.resetState()
        recycler_view_most_popular_movies.scrollToPosition(0)
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        text_view_empty_result.visibility = View.GONE
    }

    override fun showEmptyView() {
        text_view_empty_result.visibility = View.VISIBLE
    }
    override fun hideSearchMessage() {
        text_view_search_movie_hint.visibility = View.GONE
    }

    override fun showSearchMessage() {
        text_view_search_movie_hint.visibility = View.VISIBLE
    }

    override fun showBottomProgressBar() {
        bottom_progress_bar.visibility = View.VISIBLE
    }

    override fun hideBottomProgressBar() {
        bottom_progress_bar.visibility = View.GONE
    }
}