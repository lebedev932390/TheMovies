package com.evgeny.lebedev.themovies;


import android.content.SharedPreferences;

import com.evgeny.lebedev.themovies.Model.Cast;
import com.evgeny.lebedev.themovies.Model.Crew;
import com.evgeny.lebedev.themovies.Model.Image;
import com.evgeny.lebedev.themovies.Model.Movie;

import java.util.List;

public interface Contracts {

    interface View {
        interface Authentication {
            void needAuthentication();
            void showError(String error);
            void missionComplete();
        }


        interface ListOfMovies{
            void showListOfMovies(List<Movie> listOfMovies, boolean noMore);
            void showMoreMovies(List<Movie> moreMovies, boolean noMore);
        }

        interface SearchMovie{
            void showListOfMovies(List<Movie> listOfMovies, boolean noMore);
            void showMoreMovies(List<Movie> moreMovies, boolean noMore);

        }

        interface ChosenMovie {
            void showMovieInfo(String title,
                               String tagline,
                               String overview,
                               int rating,
                               String posterUrl,
                               String homepage,
                               String budget,
                               String revenue,
                               String runtime,
                               String releaseDate);
            void showMovieRestData(String trailer);
            void showMovieAccountStates(boolean favorite, boolean watchlist);
            void showMovieCredits(List<Cast> listOfCasts, List<Crew> listOfCrew);
            void showRecommendedMovies(List<Movie> listOfMovies);
            void showMovieImages(List<Image> listOfImages);


        }

        interface Person {
            void showDetails(String profilePath, String name, String department,String biography, String placeOfBirth, String birthday, String deathday);
            void showCredits(List<Movie> castList, List<Movie> crewList);
            void showImages(List<Image> imageList);
        }


    }

    interface Presenter {
        interface Authentication {
            void buttonSignInClicked(String username, String password, boolean rememberMe);
        }

        interface ListOfMovies{
            void loadMore();

        }
        interface SearchMovie{
            void searchMovie(String query);
            void loadMore();

        }
        interface ChosenMovie {
            void addToFavorites(boolean add);
            void addToWatchList(boolean add);
        }

        interface Person {
        }
    }
}

