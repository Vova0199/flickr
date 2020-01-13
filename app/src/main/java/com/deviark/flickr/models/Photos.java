package com.deviark.flickr.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Photos {

    @SerializedName("photo")
    private List<PhotoModel> photo = null;

    public List<PhotoModel> getPhoto() {
        return photo;
    }

}

