package com.deviark.flickr.retrofit;

import com.deviark.flickr.models.PhotoResponce;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    String BASE_URL = "https://www.flickr.com/";

    @GET("services/rest/?method=flickr.interestingness.getList")
    Call<PhotoResponce> getPhotos();
}
