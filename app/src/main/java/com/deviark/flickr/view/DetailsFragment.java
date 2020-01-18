package com.deviark.flickr.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.deviark.flickr.R;
import com.deviark.flickr.view.favourite.FavouriteViewModel;
import com.deviark.flickr.models.PhotoDBModel;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class DetailsFragment extends Fragment {
    public static final String EXTRA_ID = "com.deviark.flickr.EXTRA_ID";
    public static final String EXTRA_URL = "com.deviark.flickr.EXTRA_URL";
    private ImageView ivPhoto, ivFavourite;
    private Button btnSave;
    private String imageUrl;
    private PhotoDBModel photoDBModel;
    private Boolean mExists;
    private long id;
    private FavouriteViewModel photoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        initialization(view);


        btnSave.setOnClickListener(view1 -> save(mExists));
        return view;
    }

    private void initialization(View view) {
        photoViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);

        ivPhoto = view.findViewById(R.id.ivPhoto);
        ivFavourite = view.findViewById(R.id.ivFavourite);
        btnSave = view.findViewById(R.id.btnSave);

        Bundle args = getArguments();
        imageUrl = args.getString(EXTRA_URL, "");
        id = args.getLong(EXTRA_ID, 0);

        checkPhoto();
        Picasso.get().load(imageUrl).into(ivPhoto);

        photoDBModel = new PhotoDBModel(id, imageUrl);

    }


    private void hideItems(boolean b) {
        if (b) {
            ivFavourite.setVisibility(View.INVISIBLE);
            btnSave.setText("Add to favourite");
        } else {
            ivFavourite.setVisibility(View.VISIBLE);
            btnSave.setText("Remove from favourite");
        }
    }

    private void save(boolean b) {
        if (b) {
            photoViewModel.insert(photoDBModel);
            hideItems(false);
            mExists = false;
        } else {
            photoViewModel.delete(photoDBModel);
            hideItems(true);
            mExists = true;
        }
    }

    private void checkPhoto() {
        photoViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);

        photoViewModel.setFilter(id);
        photoViewModel.checkById().observe(this, photos -> {
                mExists = photos.isEmpty();
                hideItems(mExists);
        });

    }

}

