package com.marcelo.privalia.moviesapp.data.network;

public class ApiService {

    private static MetaApiEndPoint mApiEndpoint;

    public ApiService(){}

    public static MetaApiEndPoint getApiEndPoint(){
        if (mApiEndpoint==null) {
            mApiEndpoint = new MetaApiEndPoint();
        }
        return mApiEndpoint;
    }
}
