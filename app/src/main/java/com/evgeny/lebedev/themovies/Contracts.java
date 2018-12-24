package com.evgeny.lebedev.themovies;



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


        interface MoviesList {
            void showListOfMovies(List<Movie> movieList, boolean noMore);

            void showMoreMovies(List<Movie> moreMovies, boolean noMore);
        }

        interface SearchMovie {
            void showListOfMovies(List<Movie> movieList, boolean noMore);

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

            void showMovieTrailer(String trailer);

            void showMovieAccountStates(boolean favorite, boolean watchlist);

            void showMovieCredits(List<Cast> castList, List<Crew> crewList);

            void showRecommendedMovies(List<Movie> moviesList);

            void showMovieImages(List<Image> imagesList);


        }

        interface Person {
            void showDetails(String profilePath, String name, String department, String biography, String placeOfBirth, String birthday, String deathday);

            void showCredits(List<Movie> castList, List<Movie> crewList);

            void showImages(List<Image> imageList);
        }


    }

    interface Presenter {
        interface Authentication {
            void buttonSignInClicked(String username, String password, boolean rememberMe);
        }

        interface MoviesList {
            void loadMore();

        }

        interface SearchMovie {
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

