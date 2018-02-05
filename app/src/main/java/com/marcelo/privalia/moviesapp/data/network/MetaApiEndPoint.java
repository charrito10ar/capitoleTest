package com.marcelo.privalia.moviesapp.data.network;

import com.marcelo.privalia.moviesapp.data.bodies.MovieBody;
import com.marcelo.privalia.moviesapp.data.bodies.MoviesResponseBody;
import com.marcelo.privalia.moviesapp.data.bodies.SearchBody;
import com.marcelo.privalia.moviesapp.data.network.interfaces.ApiEndpointInterface;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.marcelo.privalia.moviesapp.data.utilities.DataConstants.API_END_POINT;

public class MetaApiEndPoint {

    private ApiEndpointInterface mApi;

    public MetaApiEndPoint(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new com.marcelo.privalia.moviesapp.data.network.Interceptor());
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_END_POINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(ApiEndpointInterface.class);
    }

    public Call<List<MovieBody>> getMostPopular(int page){
        return mApi.getMostPopular(page);
    }

    public Call<List<SearchBody>> searchMovie(int page, String query, String fields){
        return mApi.searchMovie(page, query, fields);
    }
}