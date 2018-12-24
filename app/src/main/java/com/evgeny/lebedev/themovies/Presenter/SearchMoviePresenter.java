package com.evgeny.lebedev.themovies.Presenter;

import android.util.Log;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.ListOfMovies;
import com.evgeny.lebedev.themovies.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviePresenter implements Contracts.Presenter.SearchMovie {
    private Contracts.View.SearchMovie view;
    private int totalPages = 1;
    private int currentPage = 1;
    private String searchQuery;

    public SearchMoviePresenter(Contracts.View.SearchMovie view) {
        this.view = view;
    }


    @Override
    public void searchMovie(String query) {
        searchQuery = query;
        App.getApi().getSearchMovie(App.apiKey,query, currentPage)
                .enqueue(new Callback<ListOfMovies>() {
                    @Override
                    public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                        if (response.isSuccessful()) {
                            totalPages = response.body().getTotalPages();
                            if (currentPage == totalPages) {
                                view.showListOfMovies(response.body().getListOfMovies(), true);

                            } else if (currentPage < totalPages) {
                                view.showListOfMovies(response.body().getListOfMovies(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ListOfMovies> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadMore() {
        Log.e("current", Integer.toString(currentPage));
        Log.e("total", Integer.toString(totalPages));
        if (currentPage<totalPages){
            currentPage++;
            App.getApi().getSearchMovie(App.apiKey,searchQuery, currentPage)
                    .enqueue(new Callback<ListOfMovies>() {
                        @Override
                        public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                            if (response.isSuccessful()){
                                if (currentPage == totalPages) {
                                    view.showMoreMovies(response.body().getListOfMovies(), true);

                                } else if (currentPage < totalPages) {
                                    view.showMoreMovies(response.body().getListOfMovies(), false);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ListOfMovies> call, Throwable t) {

                        }
                    });
        }
    }
}
