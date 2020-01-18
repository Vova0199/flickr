package com.deviark.flickr.models;

import com.google.gson.annotations.SerializedName;


public class PhotoModel {

    private long id;
    private String title;
    @SerializedName("url_s")
    private String urlS;

    public PhotoModel(long id, String urlS) {
        this.id = id;
        this.urlS = urlS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlS() {
        return urlS;
    }


}