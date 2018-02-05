package com.marcelo.privalia.moviesapp.data.network;

import android.support.annotation.NonNull;

import com.marcelo.privalia.moviesapp.data.utilities.DataConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor implements okhttp3.Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("extended", "full")
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url)
                .addHeader("trakt-api-key", DataConstants.TRAKT_API_KEY);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
