package com.deviark.flickr.view.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deviark.flickr.R;
import com.deviark.flickr.models.PhotoDBModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class FavouriteAdapter extends ListAdapter<PhotoDBModel, FavouriteAdapter.NoteHolder> {

    private OnItemClickListener listener;

    public FavouriteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PhotoDBModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<PhotoDBModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull PhotoDBModel oldItem, @NonNull PhotoDBModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PhotoDBModel oldItem, @NonNull PhotoDBModel newItem) {
            return false;
        }
    };


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        PhotoDBModel photoDBModel = getItem(position);
        Picasso.get().load(photoDBModel.getUrlS()).into(holder.ivPicture);

    }


    public PhotoDBModel getPhoto(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        private ImageView imFav;
        NoteHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);

            imFav = itemView.findViewById(R.id.ivFavourite);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PhotoDBModel note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}