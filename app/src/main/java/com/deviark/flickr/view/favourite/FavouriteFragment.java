package com.deviark.flickr.view.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deviark.flickr.R;
import com.deviark.flickr.view.Adapters.FavouriteAdapter;
import com.deviark.flickr.view.DetailsFragment;
import com.deviark.flickr.models.PhotoDBModel;
import com.deviark.flickr.view.popular.PopularFragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FavouriteFragment extends Fragment {


    public static FavouriteFragment newInstance() {
        return new FavouriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        initialization(view);

        return view;
    }

    private void initialization(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rvPhotos);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), PopularFragment.NUMBER_OF_COLUMNS));

        final FavouriteAdapter adapter = new FavouriteAdapter();
        recyclerView.setAdapter(adapter);

        FavouriteViewModel photoViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        photoViewModel.getAllNotes().observe(this, adapter::submitList);
        adapter.setOnItemClickListener(this::startDetails);
    }


    private void startDetails(PhotoDBModel photo) {
        DetailsFragment nextFrag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(DetailsFragment.EXTRA_URL, photo.getUrlS());
        args.putLong(DetailsFragment.EXTRA_ID, photo.getId());
        nextFrag.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }
}
