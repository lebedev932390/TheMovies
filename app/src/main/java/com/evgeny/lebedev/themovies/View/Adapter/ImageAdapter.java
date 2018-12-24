package com.evgeny.lebedev.themovies.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evgeny.lebedev.themovies.Model.Image;
import com.evgeny.lebedev.themovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolderImage> {

    private List<Image> listOfImages;


    private boolean isVertical;




    public static class ViewHolderImage extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolderImage(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image_image_view);

        }

    }

    public ImageAdapter(List<Image> listOfImages, boolean isVertical) {
        this.listOfImages = listOfImages;
        this.isVertical = isVertical;
    }

    @NonNull
    @Override
    public ViewHolderImage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_image, viewGroup, false);
        ViewHolderImage viewHolderImage = new ViewHolderImage(view);
        return viewHolderImage;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImage viewHolderImage, int i) {
        Image image = listOfImages.get(i);

        if (isVertical){
            Picasso.get().load(image.getFilePathVertical())
                    .placeholder(R.drawable.placeholder_vertical)
                    .into(viewHolderImage.imageView);
        }
        else {
            Picasso.get().load(image.getFilePathHorizontal())
                    .placeholder(R.drawable.placeholder_horizontal)
                    .into(viewHolderImage.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return listOfImages.size();
    }


}
