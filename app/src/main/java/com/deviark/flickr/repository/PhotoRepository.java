package com.deviark.flickr.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.deviark.flickr.DB.PhotoDAO;
import com.deviark.flickr.DB.PhotoDatabase;
import com.deviark.flickr.models.PhotoDBModel;
import com.deviark.flickr.models.PhotoModel;
import com.deviark.flickr.models.PhotoResponce;
import com.deviark.flickr.retrofit.ApiService;
import com.deviark.flickr.retrofit.RetrofitRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PhotoRepository {
    private PhotoDAO mPhotoDAO;
    private LiveData<List<PhotoDBModel>> mPhotoList;

    private static final String TAG = PhotoRepository.class.getSimpleName();
    private ApiService apiRequest;

    public PhotoRepository(Application application) {
        PhotoDatabase database = PhotoDatabase.getInstance(application);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiService.class);
        mPhotoDAO = database.photoDAO();
        mPhotoList = mPhotoDAO.getAllPhotos();

    }

    public void insert(PhotoDBModel photo) {
        new InsertPhotoAsyncTask(mPhotoDAO).execute(photo);
    }

    public void delete(PhotoDBModel photo) {
        new DeletePhotoAsyncTask(mPhotoDAO).execute(photo);
    }

    public LiveData<List<PhotoDBModel>> checkById(long id) {
        return mPhotoDAO.checkById(id);
    }

    public LiveData<List<PhotoDBModel>> getPhotosFromDB() {
        return mPhotoList;
    }

    private static class InsertPhotoAsyncTask extends AsyncTask<PhotoDBModel, Void, Void> {
        private PhotoDAO photoDAO;

        private InsertPhotoAsyncTask(PhotoDAO photoDAO) {
            this.photoDAO = photoDAO;
        }

        @Override
        protected Void doInBackground(PhotoDBModel... photos) {
            photoDAO.insert(photos[0]);
            return null;
        }
    }

    private static class DeletePhotoAsyncTask extends AsyncTask<PhotoDBModel, Void, Void> {
        private PhotoDAO photoDAO;

        private DeletePhotoAsyncTask(PhotoDAO photoDAO) {
            this.photoDAO = photoDAO;
        }

        @Override
        protected Void doInBackground(PhotoDBModel... photos) {
            photoDAO.delete(photos[0]);
            return null;
        }
    }

    public LiveData<List<PhotoModel>> getPhotosFromAPI() {
        final MutableLiveData<List<PhotoModel>> data = new MutableLiveData<>();

        apiRequest.getPhotos().enqueue(new Callback<PhotoResponce>() {

            @Override
            public void onResponse(Call<PhotoResponce> call, Response<PhotoResponce> response) {
                Log.d(TAG, "onResponse response:: " + response);


                if (response.body() != null) {
                    data.setValue(response.body().getPhotos().getPhoto());

                    Log.d(TAG, "photos total result:: " + response.body().getPhotos().getPhoto());
                }
            }

            @Override
            public void onFailure(Call<PhotoResponce> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}