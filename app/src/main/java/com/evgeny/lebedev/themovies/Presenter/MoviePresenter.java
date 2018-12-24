package com.evgeny.lebedev.themovies.Presenter;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.BodyFavorite;
import com.evgeny.lebedev.themovies.Model.BodyWatchlist;
import com.evgeny.lebedev.themovies.Model.MovieImagesList;
import com.evgeny.lebedev.themovies.Model.MovieCreditsList;
import com.evgeny.lebedev.themovies.Model.MoviesList;
import com.evgeny.lebedev.themovies.Model.VideosList;
import com.evgeny.lebedev.themovies.Model.MovieAccountStates;
import com.evgeny.lebedev.themovies.Model.MovieDetails;

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

    private void getMovieImages() {
        App.getApi().getMovieImages(movieId, App.apiKey)
                .enqueue(new Callback<MovieImagesList>() {
                    @Override
                    public void onResponse(Call<MovieImagesList> call, Response<MovieImagesList> response) {
                        if (response.isSuccessful()) {
                            view.showMovieImages(response.body().getBackdropsList());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieImagesList> call, Throwable t) {

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
                .enqueue(new Callback<VideosList>() {
                    @Override
                    public void onResponse(Call<VideosList> call, Response<VideosList> response) {
                        if (response.isSuccessful()) {
                            for (int i = 0; i < response.body().getVideosList().size(); i++) {
                                if (response.body().getVideosList().get(i).isTrailer() && response.body().getVideosList().get(i).isYoutube()) {
                                    trailer = response.body().getVideosList().get(i).getLink();
                                    view.showMovieTrailer(trailer);
                                }
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<VideosList> call, Throwable t) {

                    }
                });


        getMovieAccountStates();
        getRecommendedMovies();
        getMovieImages();
        getMovieCredits();


    }

    private void getMovieCredits() {
        App.getApi().getMovieCredits(movieId, App.apiKey)
                .enqueue(new Callback<MovieCreditsList>() {
                    @Override
                    public void onResponse(Call<MovieCreditsList> call, Response<MovieCreditsList> response) {
                        if (response.isSuccessful()) {
                            view.showMovieCredits(response.body().getCastList(), response.body().getCrewList());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieCreditsList> call, Throwable t) {

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

    private void getRecommendedMovies() {
        App.getApi().getRecommendedMovies(movieId, App.apiKey)
                .enqueue(new Callback<MoviesList>() {
                    @Override
                    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                        if (response.isSuccessful()) {
                            view.showRecommendedMovies(response.body().getMovieList());
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {

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

