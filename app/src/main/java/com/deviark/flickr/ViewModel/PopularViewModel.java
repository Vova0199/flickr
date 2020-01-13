package com.deviark.flickr.ViewModel;

import android.app.Application;

import com.deviark.flickr.models.PhotoModel;
import com.deviark.flickr.repository.PhotoRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.annotations.NonNull;


public class PopularViewModel extends AndroidViewModel {
    private LiveData<List<PhotoModel>> articleResponseLiveData;

    public PopularViewModel(@NonNull Application application) {
        super(application);
        PhotoRepository repository = new PhotoRepository(application);
        this.articleResponseLiveData = repository.getPhotosFromAPI();
    }

    public LiveData<List<PhotoModel>> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
