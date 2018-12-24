package com.evgeny.lebedev.themovies.Presenter;

import android.content.Intent;
import android.util.Log;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.ListOfMovies;
import com.evgeny.lebedev.themovies.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfMoviesPresenter implements Contracts.Presenter.ListOfMovies {
    private Contracts.View.ListOfMovies view;
    private List<Movie> listOfMovies;
    private int totalPages = 1;
    private int currentPage = 1;
    private int listType;

    public ListOfMoviesPresenter(Contracts.View.ListOfMovies view, int listType) {
        this.view = view;
        this.listType = listType;
        switch (listType) {
            case 0:
                break;
            case 1:
                getPopularMovies();
                break;
            case 2:
                break;
            case 3:
                getTopRatedMovies();
                break;
            case 4:
                getFavoriteMovies();
                break;
            case 5:
                getWatchlist();
                break;

        }
    }

    private void getFavoriteMovies() {
        App.getApi().getFavoriteMovies(currentPage, App.apiKey, App.sessionId, App.DESC)
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

    private void getWatchlist() {
        App.getApi().getWatchlist(currentPage, App.apiKey, App.sessionId, App.ASC)
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

    private void getPopularMovies() {
        App.getApi().getPopularMovies(currentPage, App.apiKey)
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

    private void getTopRatedMovies() {
        App.getApi().getTopRatedMovies(currentPage, App.apiKey)
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


        if (currentPage < totalPages) {
            currentPage++;

            Log.e("currentPage", Integer.toString(currentPage));
            Log.e("totalPages", Integer.toString(totalPages));


            switch (listType) {
                case 0:
                    break;
                case 1:
                    App.getApi().getPopularMovies(currentPage, App.apiKey)
                            .enqueue(new Callback<ListOfMovies>() {
                                @Override
                                public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                                    if (response.isSuccessful()) {
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
                    break;
                case 2:
                    break;
                case 3:
                    App.getApi().getTopRatedMovies(currentPage, App.apiKey)
                            .enqueue(new Callback<ListOfMovies>() {
                                @Override
                                public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                                    if (response.isSuccessful()) {
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
                    break;
                case 4:
                    Log.e("showMore", "asd");

                    App.getApi().getFavoriteMovies(2, App.apiKey, App.sessionId, App.DESC)
                            .enqueue(new Callback<ListOfMovies>() {
                                @Override
                                public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                                    if (currentPage == totalPages) {
                                        Log.e("lastPage", "true");

                                        view.showMoreMovies(response.body().getListOfMovies(), true);

                                    } else if (currentPage < totalPages) {
                                        view.showMoreMovies(response.body().getListOfMovies(), false);
                                    }
                                    Log.e("response", response.message());
                                }

                                @Override
                                public void onFailure(Call<ListOfMovies> call, Throwable t) {

                                }
                            });
                    break;
                case 5:
                    App.getApi().getWatchlist(currentPage, App.apiKey, App.sessionId, App.ASC)
                            .enqueue(new Callback<ListOfMovies>() {
                                @Override
                                public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                                    if (currentPage == totalPages) {
                                        view.showMoreMovies(response.body().getListOfMovies(), true);

                                    } else if (currentPage < totalPages) {
                                        view.showMoreMovies(response.body().getListOfMovies(), false);
                                    }
                                }

                                @Override
                                public void onFailure(Call<ListOfMovies> call, Throwable t) {

                                }
                            });
                    break;

            }
        }


    }


}
