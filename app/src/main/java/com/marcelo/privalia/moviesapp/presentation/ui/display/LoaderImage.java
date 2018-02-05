package com.marcelo.privalia.moviesapp.presentation.ui.display;

import android.content.Context;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.marcelo.privalia.moviesapp.R;
import com.marcelo.privalia.moviesapp.data.utilities.DataConstants;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class LoaderImage implements LoaderImageInterface{
    private static LoaderImage instance;
    private static OkHttpClient client;

    private LoaderImage(){
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();
                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("apikey", DataConstants.IMDB_API_KEY) // ApiKey from http://www.omdbapi.com/
                                .build();
                        Request newRequest = chain.request().newBuilder()
                                .url(url)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    public static synchronized LoaderImage getInstance(Context context){
        if(instance == null){
            instance = new LoaderImage();
            Picasso picasso = new Picasso.Builder(context)
                    .downloader(new OkHttp3Downloader(client))
                    .build();
            Picasso.setSingletonInstance(picasso);
        }
        return instance;
    }

    @Override
    public void loadCoverMovie(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).error(R.drawable.ic_cover_placeholder).placeholder(R.drawable.ic_cover_placeholder).into(imageView);
    }
}
