package com.evgeny.lebedev.themovies.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.Presenter.MovieListPresenter;
import com.evgeny.lebedev.themovies.R;
import com.evgeny.lebedev.themovies.View.Adapter.MovieAdapter;

import java.util.List;

public class MovieListActivityList extends AppCompatActivity implements Contracts.View.MoviesList {

    private Contracts.Presenter.MoviesList presenter;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private GridLayoutManager layoutManager;
    private Context context;
    private List<Movie> list;
    private ProgressBar progressBar;
    boolean progressBarIsVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        progressBar = findViewById(R.id.list_of_movies_progres_bar);

        context = this;
        presenter = new MovieListPresenter(this, getIntent().getIntExtra("listType", 0));
    }

    @Override
    public void showListOfMovies(final List<Movie> movieList, boolean noMore) {
        recyclerView = findViewById(R.id.list_of_movies_recyclerview);

        if (noMore) {
            progressBar.setVisibility(View.GONE);
            progressBarIsVisible = false;
            recyclerView.setPadding(0, 0, 0, 0);

        }
        list = movieList;
        adapter = new MovieAdapter(list, true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    final int visibleThreshold = 2;

                    GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();

                    if (currentTotalCount <= lastItem + visibleThreshold) {
                        if (progressBarIsVisible) {
                            progressBar.setVisibility(View.VISIBLE);
                            presenter.loadMore();

                        }

                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("id", movieList.get(position).getId());
                startActivity(intent);
            }
        });

    }


    @Override
    public void showMoreMovies(List<Movie> moreMovies, boolean noMore) {

        if (noMore) {
            progressBar.setVisibility(View.GONE);
            progressBarIsVisible = false;
            recyclerView.setPadding(0, 0, 0, 0);

        }
        list.addAll(moreMovies);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);

    }


}
