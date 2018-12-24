package com.evgeny.lebedev.themovies;


import android.graphics.Movie;

import com.evgeny.lebedev.themovies.Model.BodyFavorite;
import com.evgeny.lebedev.themovies.Model.BodyWatchlist;
import com.evgeny.lebedev.themovies.Model.MovieImagesList;
import com.evgeny.lebedev.themovies.Model.MovieCreditsList;
import com.evgeny.lebedev.themovies.Model.MoviesList;
import com.evgeny.lebedev.themovies.Model.PersonImagesList;
import com.evgeny.lebedev.themovies.Model.PersonMovieCreditsList;
import com.evgeny.lebedev.themovies.Model.VideosList;
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
    Call<PersonMovieCreditsList> getPersonMovieCredits(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/images")
    Call<PersonImagesList> getPersonImages(@Path("person_id") int personId, @Query("api_key") String apiKey);

    //movie
    //@GET("movie/{movie_id}")
    //Call<Movie> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<VideosList> getMovieVideos(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/account_states")
    Call<MovieAccountStates> getMovieAccountStates(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @GET("account/{account_id}/favorite/movies")
    Call<MoviesList> getFavoriteMovies(@Query("page") int page, @Query("api_key") String apiKey, @Query("session_id") String sessionId, @Query("sort_by") String sortBy);

    @GET("account/{account_id}/watchlist/movies")
    Call<MoviesList> getWatchlist(@Query("page") int page, @Query("api_key") String apiKey, @Query("session_id") String sessionId, @Query("sort_by") String sortBy);

    @GET("movie/{movie_id}/credits")
    Call<MovieCreditsList> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MoviesList> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);


    @GET("movie/{movie_id}/images")
    Call<MovieImagesList> getMovieImages(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


    @GET("authentication/token/new")
    Call<RequestToken> getRequestToken(@Query("api_key") String apiKey);


    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    Call<RequestToken> createSessionWithLogin(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("request_token") String requestToke,
                                              @Query("api_key") String apiKey);


    @GET("movie/{movie_id}/recommendations")
    Call<MoviesList> getRecommendedMovies(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


    @GET("movie/popular")
    Call<MoviesList> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);


    @GET("movie/top_rated")
    Call<MoviesList> getTopRatedMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @FormUrlEncoded
    @POST("authentication/session/new")
    Call<Session> createSession(@Field("request_token") String requestToken,
                                @Query("api_key") String apiKey);

    @POST("account/{account_id}/favorite")
    Call<Response> markAsFavorite(@Body BodyFavorite bodyFavorite,
                                  @Query("api_key") String apiKey,
                                  @Query("session_id") String sessionId);

    @POST("account/{account_id}/watchlist")
    Call<Response> addToWatchList(@Body BodyWatchlist bodyWatchlist,
                                  @Query("api_key") String apiKey,
                                  @Query("session_id") String sessionId);


}
