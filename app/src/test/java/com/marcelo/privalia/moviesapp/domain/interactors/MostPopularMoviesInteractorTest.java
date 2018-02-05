package com.marcelo.privalia.moviesapp.domain.interactors;

import com.marcelo.privalia.moviesapp.data.dao.MovieDAO;
import com.marcelo.privalia.moviesapp.data.dao.MovieDAOInterface;

import com.marcelo.privalia.moviesapp.presentation.ui.fragments.MostPopularMoviesPresenterInterface;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MostPopularMoviesInteractorTest {

    @Mock
    private MostPopularMoviesPresenterInterface presenterInterface;

    private MostPopularMoviesInteractor interactor;

    @Mock
    private MovieDAOInterface movieDAOInterface;

    @Mock
    private MovieDAO.SearchMoviesListener searchMoviesListener;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        interactor = new MostPopularMoviesInteractor(presenterInterface);
    }
}