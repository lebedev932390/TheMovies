package com.evgeny.lebedev.themovies.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.Presenter.SearchMoviePresenter;
import com.evgeny.lebedev.themovies.R;
import com.evgeny.lebedev.themovies.View.Adapter.MovieAdapter;

import java.util.List;

public class SearchMovieActivity extends AppCompatActivity implements Contracts.View.SearchMovie {
    private Contracts.Presenter.SearchMovie presenter;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private EditText editTextSearchMovie;

    private List<Movie> list;
    private ProgressBar progressBar;
    boolean progressBarIsVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        context = this;
        progressBar = findViewById(R.id.search_movie_progres_bar);
        presenter = new SearchMoviePresenter(this);
        editTextSearchMovie = findViewById(R.id.search_movie_edit_text);
        editTextSearchMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchMovie(editTextSearchMovie.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void showListOfMovies(final List<Movie> movieList, boolean noMore) {
        recyclerView = findViewById(R.id.search_movie_list_recyclerview);

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
            progressBarIsVisible = false;
            progressBar.setVisibility(View.GONE);
            recyclerView.setPadding(0, 0, 0, 0);
        }
        list.addAll(moreMovies);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
