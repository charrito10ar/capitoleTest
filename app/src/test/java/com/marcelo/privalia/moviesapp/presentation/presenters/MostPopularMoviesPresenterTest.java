package com.marcelo.privalia.moviesapp.presentation.presenters;

import com.marcelo.privalia.moviesapp.domain.model.Movie;
import com.marcelo.privalia.moviesapp.presentation.ui.interfaces.MostPopularMoviesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MostPopularMoviesPresenterTest {

    @Mock
    private MostPopularMoviesView view;

    private MostPopularMoviesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MostPopularMoviesPresenter(view);
    }

    @Test
    public void loadMostPopularMoviesShowLoadingViewSuccessTest() {
        presenter.loadMostPopularMovies();
        Mockito.verify(view).showLoading();
    }

    @Test
    public void loadMostPopularMoviesErrorTest() {
        presenter.getMostPopularError("Error");
        Mockito.verify(view).showError("Error");
    }

    @Test
    public void loadMostPopularMoviesSuccessfulTest() {
        presenter.getMostPopularSuccessful(Mockito.<Movie>anyList(), 1);
        Mockito.verify(view).loadMostPopularMoviesSuccessful(Mockito.<Movie>anyList());
    }

    @Test
    public void resetListTest() {
        presenter.resetList();
        Mockito.verify(view).resetList();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).hideEmptyView();
        Mockito.verify(view).hideSearchMessage();
    }

    @Test
    public void showEmptyViewTest() {
        presenter.showEmptyView();
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).showEmptyView();
    }

    @Test
    public void hideEmptyViewTest() {
        presenter.hideEmptyView();
        Mockito.verify(view).hideEmptyView();
    }
}
