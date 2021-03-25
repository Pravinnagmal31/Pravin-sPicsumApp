package com.vedam.pravinpicsum.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient
{
    private static final String URL = "https://picsum.photos/";
    public static Retrofit retrofit = null;

    public static APIInterface getApiClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().
                    baseUrl(URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit.create(APIInterface.class);
    }

}
