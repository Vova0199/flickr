package com.deviark.flickr.view.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deviark.flickr.R;
import com.deviark.flickr.view.Adapters.PopularAdapter;
import com.deviark.flickr.models.PhotoModel;
import com.deviark.flickr.view.DetailsFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class PopularFragment extends Fragment implements PopularAdapter.ItemClickListener {

    public static final int NUMBER_OF_COLUMNS = 3;
    private PopularAdapter adapter;
    @NonNull
    private PopularViewModel photoViewModel;

    private ArrayList<PhotoModel> photos = new ArrayList<>();

    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        initialization(view);
        getMovieArticles();
        return view;
    }


    private void initialization(View view) {
        // View Model
        photoViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);


        RecyclerView recyclerView = view.findViewById(R.id.rvPhotos);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_OF_COLUMNS));

        adapter = new PopularAdapter(getActivity(), photos);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        DetailsFragment nextFrag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(DetailsFragment.EXTRA_URL, adapter.getItem(position).getUrlS());
        args.putLong(DetailsFragment.EXTRA_ID, adapter.getItem(position).getId());
        nextFrag.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }


    private void getMovieArticles() {
        photoViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                List<PhotoModel> articles = articleResponse;
                photos.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }
}

