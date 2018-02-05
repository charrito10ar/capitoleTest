package com.marcelo.privalia.moviesapp.data.dao;

import com.marcelo.privalia.moviesapp.data.bodies.MovieBody;
import com.marcelo.privalia.moviesapp.data.bodies.MoviesResponseBody;
import com.marcelo.privalia.moviesapp.data.bodies.SearchBody;
import com.marcelo.privalia.moviesapp.data.bodies.SearchResponseBody;
import com.marcelo.privalia.moviesapp.data.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDAO implements MovieDAOInterface {

    private  Call<List<SearchBody>> searchMovieCall;

    @Override
    public void getMostPopular(int page, final MostPopularMoviesListener mListener) {
        final Call<List<MovieBody>> call = ApiService.getApiEndPoint().getMostPopular(page);
        call.enqueue(new Callback<List<MovieBody>>() {
            @Override
            public void onResponse(Call<List<MovieBody>> call, Response<List<MovieBody>> response) {
                if(response.code() == 200){
                    MoviesResponseBody moviesResponseBody = new MoviesResponseBody();
                    moviesResponseBody.setMovieBodyList(response.body());
                    moviesResponseBody.setTotal_pages(Integer.valueOf(response.headers().get("x-pagination-page-count")));
                    moviesResponseBody.setTotal_results(Integer.valueOf(response.headers().get("x-pagination-item-count")));
                    moviesResponseBody.setPage(Integer.valueOf(response.headers().get("x-pagination-page")));
                    mListener.getMostPopularSuccessful(moviesResponseBody);
                }else{
                    mListener.getMostPopularError("Error al Cargar peliculas: " + "ErrorCode: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MovieBody>> call, Throwable t) {
                mListener.getMostPopularError("Error al cargar peliculas: " + t.getMessage());
            }
        });
    }

    @Override
    public void searchMovie(int page, String query, final SearchMoviesListener mListener) {
        if(searchMovieCall != null){
            if(searchMovieCall.isExecuted())
                searchMovieCall.cancel();
        }
        searchMovieCall = ApiService.getApiEndPoint().searchMovie(page, query, "title");
        searchMovieCall.enqueue(new Callback<List<SearchBody>>() {
            @Override
            public void onResponse(Call<List<SearchBody>> call, Response<List<SearchBody>> response) {
                if(response.code() == 200){
                    SearchResponseBody searchResponseBody = new SearchResponseBody();
                    searchResponseBody.setResults(response.body());
                    searchResponseBody.setTotal_pages(Integer.valueOf(response.headers().get("x-pagination-page-count")));
                    searchResponseBody.setTotal_results(Integer.valueOf(response.headers().get("x-pagination-item-count")));
                    searchResponseBody.setPage(Integer.valueOf(response.headers().get("x-pagination-page")));
                    mListener.searchMovieSuccessful(searchResponseBody);
                }else{
                    mListener.searchMovieError("Error al Cargar peliculas: " + "ErrorCode: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SearchBody>> call, Throwable t) {
                if(!call.isCanceled())
                    mListener.searchMovieError("Error al cargar peliculas: " + t.getMessage());
            }
        });
    }

    public interface MostPopularMoviesListener{
        void getMostPopularSuccessful(MoviesResponseBody moviesResponseBody);
        void getMostPopularError(String message);
    }

    public interface SearchMoviesListener{
        void searchMovieSuccessful(SearchResponseBody searchResponseBody);
        void searchMovieError(String message);
    }
}