package com.deviark.flickr.models;

import com.google.gson.annotations.SerializedName;


public class PhotoResponce {
    @SerializedName("photos")
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }


}
