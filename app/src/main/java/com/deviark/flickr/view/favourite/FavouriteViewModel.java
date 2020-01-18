package com.deviark.flickr.view.favourite;

import android.app.Application;

import com.deviark.flickr.models.PhotoDBModel;
import com.deviark.flickr.repository.PhotoRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


public class FavouriteViewModel extends AndroidViewModel {
    private PhotoRepository repository;
    private LiveData<List<PhotoDBModel>> allNotes;
    private MediatorLiveData<List<PhotoDBModel>> searchByLiveData  = new MediatorLiveData<>();

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        repository = new PhotoRepository(application);
        allNotes = repository.getPhotosFromDB();
    }

    public void insert(PhotoDBModel photoDBModel) {
        repository.insert(photoDBModel);
    }

    public void delete(PhotoDBModel photoDBModel) {
        repository.delete(photoDBModel);
    }

    public void setFilter(long id) {
        LiveData<List<PhotoDBModel>> repositoryLiveData = this.repository.checkById(id);
        searchByLiveData.addSource(repositoryLiveData, exercisList -> {
            searchByLiveData.removeSource(repositoryLiveData);
            searchByLiveData.setValue(exercisList);
        });
    }

    public LiveData<List<PhotoDBModel>> checkById() {
        return searchByLiveData;
    }

    LiveData<List<PhotoDBModel>> getAllNotes() {
        return allNotes;
    }
}