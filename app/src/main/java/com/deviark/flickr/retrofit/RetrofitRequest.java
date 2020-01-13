package com.deviark.flickr.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitRequest {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createOkHttpClient())
                    .build();
        }
        return retrofit;
    }
    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new ApiKeyInterceptor());

        return httpClient.build();
    }
}

