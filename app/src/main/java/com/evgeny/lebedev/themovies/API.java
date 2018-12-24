package com.evgeny.lebedev.themovies;


import android.graphics.Movie;

import com.evgeny.lebedev.themovies.Model.BodyFavorite;
import com.evgeny.lebedev.themovies.Model.BodyWatchlist;
import com.evgeny.lebedev.themovies.Model.LisfOfImages;
import com.evgeny.lebedev.themovies.Model.ListOfCredits;
import com.evgeny.lebedev.themovies.Model.ListOfMovies;
import com.evgeny.lebedev.themovies.Model.ListOfPersonImages;
import com.evgeny.lebedev.themovies.Model.ListOfPersonMovieCredits;
import com.evgeny.lebedev.themovies.Model.ListOfVideos;
import com.evgeny.lebedev.themovies.Model.MovieAccountStates;
import com.evgeny.lebedev.themovies.Model.MovieDetails;
import com.evgeny.lebedev.themovies.Model.Person;
import com.evgeny.lebedev.themovies.Model.RequestToken;
import com.evgeny.lebedev.themovies.Model.Response;
import com.evgeny.lebedev.themovies.Model.Session;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //person
    @GET("person/{person_id}")
    Call<Person> getPersonDetails(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/movie_credits")
    Call<ListOfPersonMovieCredits> getPersonMovieCredits(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/images")
    Call<ListOfPersonImages> getPersonImages(@Path("person_id") int personId, @Query("api_key") String apiKey);

    //movie
    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<ListOfVideos> getMovieVideos(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/account_states")
    Call<MovieAccountStates> getMovieAccountStates(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @GET("account/{account_id}/favorite/movies")
    Call<ListOfMovies> getFavoriteMovies(@Query("page") int page, @Query("api_key") String apiKey, @Query("session_id") String sessionId, @Query("sort_by") String sortBy);

    @GET("account/{account_id}/watchlist/movies")
    Call<ListOfMovies> getWatchlist(@Query("page") int page, @Query("api_key") String apiKey, @Query("session_id") String sessionId, @Query("sort_by") String sortBy);

    @GET("movie/{movie_id}/credits")
    Call<ListOfCredits> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<ListOfMovies> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);


    @GET("movie/{movie_id}/images")
    Call<LisfOfImages> getMovieImages(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


    @GET("authentication/token/new")
    Call<RequestToken> getRequestToken(@Query("api_key") String apiKey);


    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    Call<RequestToken> createSessionWithLogin(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("request_token") String requestToke,
                                              @Query("api_key") String apiKey);


    @GET("movie/{movie_id}/recommendations")
    Call<ListOfMovies> getRecommendedMovies(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


    @GET("movie/popular")
    Call<ListOfMovies> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);


    @GET("movie/top_rated")
    Call<ListOfMovies> getTopRatedMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @FormUrlEncoded
    @POST("authentication/session/new")
    Call<Session> createSession(@Field("request_token") String requestToken,
                                @Query("api_key") String apiKey);

    //@Headers("application/json;charset=utf-8")
    @POST("account/{account_id}/favorite")
    Call<Response> markAsFavorite(@Body BodyFavorite bodyFavorite,
                                  @Query("api_key") String apiKey,
                                  @Query("session_id") String sessionId);

    @POST("account/{account_id}/watchlist")
    Call<Response> addToWatchList(@Body BodyWatchlist bodyWatchlist,
                                  @Query("api_key") String apiKey,
                                  @Query("session_id") String sessionId);


}
