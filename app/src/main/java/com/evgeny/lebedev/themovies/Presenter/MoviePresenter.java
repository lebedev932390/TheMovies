package com.evgeny.lebedev.themovies.Presenter;

import android.util.Log;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.BodyFavorite;
import com.evgeny.lebedev.themovies.Model.BodyWatchlist;
import com.evgeny.lebedev.themovies.Model.LisfOfImages;
import com.evgeny.lebedev.themovies.Model.ListOfCredits;
import com.evgeny.lebedev.themovies.Model.ListOfMovies;
import com.evgeny.lebedev.themovies.Model.ListOfVideos;
import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.Model.MovieAccountStates;
import com.evgeny.lebedev.themovies.Model.MovieDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements Contracts.Presenter.ChosenMovie {
    private Contracts.View.ChosenMovie view;
    private int movieId;
    private String trailer;


    public MoviePresenter(Contracts.View.ChosenMovie view, int movieId) {
        this.view = view;
        this.movieId = movieId;

        App.getApi().getMovieDetails(movieId, App.apiKey)
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        if (response.isSuccessful()) {
                            showMovieInfo(response.body());

                        }

                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });
    }

    private void getMovieImages(){
        App.getApi().getMovieImages(movieId, App.apiKey)
                .enqueue(new Callback<LisfOfImages>() {
                    @Override
                    public void onResponse(Call<LisfOfImages> call, Response<LisfOfImages> response) {
                        if (response.isSuccessful()){
                            view.showMovieImages(response.body().getListOfBackdrops());
                        }
                    }

                    @Override
                    public void onFailure(Call<LisfOfImages> call, Throwable t) {

                    }
                });
    }

    private void showMovieInfo(final MovieDetails movieDetails) {
        double r = Double.parseDouble(movieDetails.getRating()) * 10;
        int rt = (int) r;


        view.showMovieInfo(
                movieDetails.getTitle(),
                movieDetails.getTagline(),
                movieDetails.getOverview(),
                rt,
                movieDetails.getPosterLarge(),
                movieDetails.getHomepage(),
                movieDetails.getBudget(),
                movieDetails.getRevenue(),
                movieDetails.getRuntime(),
                movieDetails.getReleaseDate());
        App.getApi().getMovieVideos(movieId, App.apiKey)
                .enqueue(new Callback<ListOfVideos>() {
                    @Override
                    public void onResponse(Call<ListOfVideos> call, Response<ListOfVideos> response) {
                        if (response.isSuccessful()) {
                            for (int i = 0; i < response.body().getListOfVideos().size(); i++) {
                                if (response.body().getListOfVideos().get(i).isTrailer() && response.body().getListOfVideos().get(i).isYoutube()) {
                                    trailer = response.body().getListOfVideos().get(i).getLink();
                                    view.showMovieRestData(trailer);
                                }
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ListOfVideos> call, Throwable t) {

                    }
                });


        getMovieAccountStates();
        getRecommendedMovies();
        getMovieImages();
        getMovieCredits();




    }

    private void getMovieCredits(){
        App.getApi().getMovieCredits(movieId, App.apiKey)
                .enqueue(new Callback<ListOfCredits>() {
                    @Override
                    public void onResponse(Call<ListOfCredits> call, Response<ListOfCredits> response) {
                        if (response.isSuccessful()) {
                            view.showMovieCredits(response.body().getListOfCast(),response.body().getListOfCrew());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListOfCredits> call, Throwable t) {

                    }
                });
    }

    private void getMovieAccountStates() {
        App.getApi().getMovieAccountStates(movieId, App.apiKey, App.sessionId)
                .enqueue(new Callback<MovieAccountStates>() {
                    @Override
                    public void onResponse(Call<MovieAccountStates> call, Response<MovieAccountStates> response) {
                        if (response.isSuccessful()) {
                            view.showMovieAccountStates(response.body().getFavorite(), response.body().getWatchlist());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieAccountStates> call, Throwable t) {

                    }
                });
    }

    private void getRecommendedMovies(){
        App.getApi().getRecommendedMovies(movieId,App.apiKey)
                .enqueue(new Callback<ListOfMovies>() {
                    @Override
                    public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                        if (response.isSuccessful()){
                            view.showRecommendedMovies(response.body().getListOfMovies());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListOfMovies> call, Throwable t) {

                    }
                });
    }
    @Override
    public void addToFavorites(boolean add) {
        App.getApi().markAsFavorite(new BodyFavorite("movie", movieId, add), App.apiKey, App.sessionId)
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.Response>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.Response> call, Response<com.evgeny.lebedev.themovies.Model.Response> response) {

                        getMovieAccountStates();
                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.Response> call, Throwable t) {

                    }
                });
    }

    @Override
    public void addToWatchList(boolean add) {

        App.getApi().addToWatchList(new BodyWatchlist("movie", movieId, add), App.apiKey, App.sessionId)
                .enqueue(new Callback<com.evgeny.lebedev.themovies.Model.Response>() {
                    @Override
                    public void onResponse(Call<com.evgeny.lebedev.themovies.Model.Response> call, Response<com.evgeny.lebedev.themovies.Model.Response> response) {

                        getMovieAccountStates();

                    }

                    @Override
                    public void onFailure(Call<com.evgeny.lebedev.themovies.Model.Response> call, Throwable t) {

                    }
                });
    }


}

