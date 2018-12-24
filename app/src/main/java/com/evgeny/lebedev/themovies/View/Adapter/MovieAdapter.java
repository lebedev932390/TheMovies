package com.evgeny.lebedev.themovies.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderMovie> {

    private boolean isVertical;
    private Context context;
    private List<Movie> listOfMovies;

    public MovieAdapter(List<Movie> listOfMovies, Context context, boolean isVertical) {
        this.listOfMovies = listOfMovies;
        this.context = context;
        this.isVertical = isVertical;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolderMovie extends RecyclerView.ViewHolder {

        public TextView textViewTitle, textViewReleaseDate;
        public ImageView imageViewPoster;

        public ViewHolderMovie(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.card_movie_title);
            textViewReleaseDate = itemView.findViewById(R.id.card_movie_release_date);
            imageViewPoster = itemView.findViewById(R.id.card_movie_poster);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolderMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (isVertical) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie_vertical, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie_horizontal, viewGroup, false);

        }
        ViewHolderMovie viewHolderMovie = new ViewHolderMovie(view, listener);
        return viewHolderMovie;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderMovie viewHolderMovie, int i) {
        final Movie movie = listOfMovies.get(i);
        viewHolderMovie.textViewTitle.setText(movie.getTitle());
        viewHolderMovie.textViewReleaseDate.setText(movie.getReleaseDate());

        Picasso.get().load(movie.getPosterSmall())
                .placeholder(R.drawable.placeholder_vertical)
                .into(viewHolderMovie.imageViewPoster);


    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }


}