package com.evgeny.lebedev.themovies.Presenter;

import android.util.Log;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListPresenter implements Contracts.Presenter.MoviesList {
    private Contracts.View.MoviesList view;
    private int totalPages = 1;
    private int currentPage = 1;
    private int listType;

    public MovieListPresenter(Contracts.View.MoviesList view, int listType) {
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
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {

                        if (response.isSuccessful()) {
                            totalPages = response.body().getTotalPages();

                            if (currentPage == totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), true);

                            } else if (currentPage < totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), false);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                    }
                });
    }

    private void getWatchlist() {
        App.getApi().getWatchlist(currentPage, App.apiKey, App.sessionId, App.ASC)
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                        if (response.isSuccessful()) {
                            totalPages = response.body().getTotalPages();

                            if (currentPage == totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), true);

                            } else if (currentPage < totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                    }
                });
    }

    private void getPopularMovies() {
        App.getApi().getPopularMovies(currentPage, App.apiKey)
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                        if (response.isSuccessful()) {
                            totalPages = response.body().getTotalPages();

                            if (currentPage == totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), true);

                            } else if (currentPage < totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                    }
                });
    }

    private void getTopRatedMovies() {
        App.getApi().getTopRatedMovies(currentPage, App.apiKey)
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                        if (response.isSuccessful()) {
                            totalPages = response.body().getTotalPages();

                            if (currentPage == totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), true);

                            } else if (currentPage < totalPages) {
                                view.showListOfMovies(response.body().getMovieList(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                    }
                });
    }


    @Override
    public void loadMore() {
        if (currentPage < totalPages) {
            currentPage++;
            switch (listType) {
                case 1:
                    App.getApi().getPopularMovies(currentPage, App.apiKey)
                            .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                                @Override
                                public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                                    if (response.isSuccessful()) {
                                        if (currentPage == totalPages) {
                                            view.showMoreMovies(response.body().getMovieList(), true);

                                        } else if (currentPage < totalPages) {
                                            view.showMoreMovies(response.body().getMovieList(), false);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                                }
                            });
                    break;
                case 2:
                    App.getApi().getTopRatedMovies(currentPage, App.apiKey)
                            .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                                @Override
                                public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                                    if (response.isSuccessful()) {
                                        if (currentPage == totalPages) {
                                            view.showMoreMovies(response.body().getMovieList(), true);

                                        } else if (currentPage < totalPages) {
                                            view.showMoreMovies(response.body().getMovieList(), false);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                                }
                            });
                    break;
                case 3:
                    App.getApi().getFavoriteMovies(2, App.apiKey, App.sessionId, App.DESC)
                            .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                                @Override
                                public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                                    if (currentPage == totalPages) {
                                        view.showMoreMovies(response.body().getMovieList(), true);

                                    } else if (currentPage < totalPages) {
                                        view.showMoreMovies(response.body().getMovieList(), false);
                                    }
                                }

                                @Override
                                public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                                }
                            });
                    break;
                case 4:
                    App.getApi().getWatchlist(currentPage, App.apiKey, App.sessionId, App.ASC)
                            .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.MoviesList>() {
                                @Override
                                public void onResponse(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Response<com.evgeny.lebedev.themovies.Model.MoviesList> response) {
                                    if (currentPage == totalPages) {
                                        view.showMoreMovies(response.body().getMovieList(), true);

                                    } else if (currentPage < totalPages) {
                                        view.showMoreMovies(response.body().getMovieList(), false);
                                    }
                                }

                                @Override
                                public void onFailure(Call<com.evgeny.lebedev.themovies.Model.MoviesList> call, Throwable t) {

                                }
                            });
                    break;

            }
        }


    }


}
