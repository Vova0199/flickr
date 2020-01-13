package com.deviark.flickr.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photo_table")
public class PhotoDBModel {

    private String title;
    private String urlS;
    @PrimaryKey(autoGenerate = false)
    private long id;

    public PhotoDBModel(long id, String urlS) {
        this.id = id;
        this.urlS = urlS;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlS() {
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }


}