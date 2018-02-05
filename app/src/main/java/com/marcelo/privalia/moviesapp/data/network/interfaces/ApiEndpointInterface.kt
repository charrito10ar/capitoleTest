package com.marcelo.privalia.moviesapp.data.network.interfaces

import com.marcelo.privalia.moviesapp.data.bodies.MovieBody
import com.marcelo.privalia.moviesapp.data.bodies.MoviesResponseBody
import com.marcelo.privalia.moviesapp.data.bodies.SearchBody
import com.marcelo.privalia.moviesapp.domain.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpointInterface{
    @GET("/movies/popular?")
    fun getMostPopular(@Query("page") page: Int): Call<List<MovieBody>>

    @GET("/search/movie?")
    fun searchMovie(@Query("page") page: Int, @Query("query") query: String, @Query("fields") fields: String): Call<List<SearchBody>>
}