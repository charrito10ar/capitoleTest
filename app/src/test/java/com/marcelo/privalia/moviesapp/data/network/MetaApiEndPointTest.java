package com.marcelo.privalia.moviesapp.data.network;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import retrofit2.Call;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MetaApiEndPointTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMostPopularTest() throws IOException {
        MetaApiEndPoint mockedApiInterface = Mockito.mock(MetaApiEndPoint.class);
        final Call mockedCall = Mockito.mock(Call.class);
        Mockito.when(mockedApiInterface.getMostPopular(1)).thenReturn(mockedCall);
        assertEquals(mockedApiInterface.getMostPopular(1), mockedCall);
    }

    @Test
    public void searchMoviesTest(){
        MetaApiEndPoint mockedApiInterface = Mockito.mock(MetaApiEndPoint.class);
        final Call mockedCall = Mockito.mock(Call.class);
        Mockito.when(mockedApiInterface.searchMovie(1, "titanic", "title")).thenReturn(mockedCall);
        assertEquals(mockedApiInterface.searchMovie(1, "titanic", "title"), mockedCall);
    }
}