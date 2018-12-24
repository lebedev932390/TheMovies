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
import com.evgeny.lebedev.themovies.Presenter.ListOfMoviesPresenter;
import com.evgeny.lebedev.themovies.R;
import com.evgeny.lebedev.themovies.View.Adapter.MovieAdapter;

import java.util.List;

public class ListOfMoviesActivity extends AppCompatActivity implements Contracts.View.ListOfMovies {

    private Contracts.Presenter.ListOfMovies presenter;
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
        setContentView(R.layout.activity_list_of_movies);

        progressBar = findViewById(R.id.list_of_movies_progres_bar);

        context = this;
        presenter = new ListOfMoviesPresenter(this, getIntent().getIntExtra("listType", 0));
    }

    @Override
    public void showListOfMovies(final List<Movie> listOfMovies, boolean noMore) {
        recyclerView = findViewById(R.id.list_of_movies_recyclerview);

        if (noMore){
            progressBar.setVisibility(View.GONE);
            progressBarIsVisible = false;
            recyclerView.setPadding(0,0,0,0);

        }
        list = listOfMovies;
        adapter = new MovieAdapter(list, this, true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        //layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        //    @Override
        //    public int getSpanSize(int position) {
//
        //        if (list.get(position).getType() == TYPE_CONTENT) {
        //            // will consume only one part of the SPAN_COUNT
        //            return 1;
        //        } else if(list.get(position).getType() == TYPE_FOOTER) {
        //            // Will consume the whole width
        //            // Will take care of spaces to be left,
        //            // if the number of views in a row is not equal to 4
        //            return layoutManager.getSpanCount();
        //        }
        //        return layoutManager.getSpanCount();
        //    }
        //});
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) { // only when scrolling up

                    final int visibleThreshold = 2;

                    GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();

                    if (currentTotalCount <= lastItem + visibleThreshold) {
                        //show your loading view
                        // load content in background

                        if (progressBarIsVisible){
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
                intent.putExtra("id",listOfMovies.get(position).getId());
                startActivity(intent);
            }
        });

    }


    @Override
    public void showMoreMovies(List<Movie> moreMovies, boolean noMore) {

        if (noMore){
            progressBar.setVisibility(View.GONE);
            progressBarIsVisible = false;
            recyclerView.setPadding(0,0,0,0);

        }
        list.addAll(moreMovies);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);

    }


}
