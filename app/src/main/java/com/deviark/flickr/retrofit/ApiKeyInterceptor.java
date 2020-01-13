package com.deviark.flickr.retrofit;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl originalHttpUrl = original.url();
        final HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "df1b1d210f7e3a9778c3455b772d52ec")
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .addQueryParameter("extras", "url_s")
                .build();

        // Request customization: add request headers
        final Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        final Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

