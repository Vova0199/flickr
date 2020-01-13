package com.deviark.flickr.view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deviark.flickr.R;
import com.deviark.flickr.models.PhotoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private List<PhotoModel> mPhoto;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public PopularAdapter(Context context, List<PhotoModel> photos) {
        this.mInflater = LayoutInflater.from(context);
        this.mPhoto = photos;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoModel photo = mPhoto.get(position);
        Picasso.get().load(photo.getUrlS()).into(holder.ivPicture);

    }

    @Override
    public int getItemCount() {
        return mPhoto.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPicture;

        ViewHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setList(List<PhotoModel> resList) {
        this.mPhoto = resList;
        notifyDataSetChanged();
    }

    public PhotoModel getItem(int id) {
        return mPhoto.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }
}