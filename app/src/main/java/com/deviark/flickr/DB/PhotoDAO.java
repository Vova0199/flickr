package com.deviark.flickr.DB;

import com.deviark.flickr.models.PhotoDBModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PhotoDAO {

    @Insert
    void insert(PhotoDBModel photo);

    @Delete
    void delete(PhotoDBModel photo);

    @Query("Select * FROM photo_table WHERE id LIKE :id")
    LiveData<List<PhotoDBModel>> checkById(long id);

    @Query("SELECT * FROM photo_table ")
    LiveData<List<PhotoDBModel>> getAllPhotos();
}

