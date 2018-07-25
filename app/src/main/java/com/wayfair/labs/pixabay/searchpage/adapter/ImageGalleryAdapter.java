package com.wayfair.labs.pixabay.searchpage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wayfair.labs.pixabay.R;
import com.wayfair.labs.pixabay.data.network.model.Photo;

import java.util.List;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageGalleryViewHolder> {

    private List<Photo> photos;

    public ImageGalleryAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public ImageGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new ImageGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryViewHolder imageGalleryViewHolder, int position) {
        imageGalleryViewHolder.photoImageView.setImageURI(photos.get(position).webformatURL);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void clear() {
        final int size = photos.size();
        if(size > 0){
            photos.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    class ImageGalleryViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView photoImageView;

        ImageGalleryViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.iv_photo);
        }

    }
}
