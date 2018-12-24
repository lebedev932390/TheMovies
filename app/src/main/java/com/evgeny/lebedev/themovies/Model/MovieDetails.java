package com.evgeny.lebedev.themovies.Model;


import com.evgeny.lebedev.themovies.App;
import com.google.gson.annotations.SerializedName;

public class MovieDetails {
    private final String imageBaseUrlSmall = "https://image.tmdb.org/t/p/300";
    private final String imageBaseUrlLarge = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    @SerializedName("vote_average")
    private String rating;
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String backdrop;
    private String budget;
    private String homepage;
    private int id;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    private String tagline;
    @SerializedName("vote_count")
    private String totalVotes;
    private String revenue;


    private String runtime;

    public String getRuntime() {
        if (runtime == null) {
            return "Runtime: " + " - ";
        }
        int rntm = Integer.parseInt(runtime);
        if (rntm % 60 > 0) {
            return "Runtime: " + Integer.toString(rntm / 60) + "h " + Integer.toString(rntm % 60) + "m";
        }
        return "Runtime: " + runtime + "m";

    }

    public String getRevenue() {
        if (revenue == null || revenue.equals("0")) {
            return "Revenue: " + " - ";
        }
        return "Revenue: $" + revenue;

    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterSmall() {
        return imageBaseUrlSmall + poster;
    }

    public String getPosterLarge() {
        return imageBaseUrlLarge + poster;
    }

    public String getBackdropSmall() {
        return imageBaseUrlSmall + backdrop;
    }

    public String getBackdropLarge() {
        return imageBaseUrlSmall + backdrop;
    }


    public String getBudget() {
        if (budget == null || budget.equals("0")) {
            return "Budget: " + " - ";
        }
        return "Budget: $" + budget;

    }

    public String getHomepage() {
        if (homepage == null) {
            return "Homepage: " + " - ";
        }
        return "Homepage: " + homepage;
    }

    public int getId() {

        return id;
    }

    public String getOverview() {
        if (overview == null) {
            return null;
        }
        return overview;
    }

    public String getReleaseDate() {
        if (releaseDate == null) {
            return null;
        }
        return App.formatDate(releaseDate);
    }


    public String getTagline() {
        if (tagline == null) {
            return null;
        }
        return tagline;
    }

    public String getTotalVotes() {

        return "Votes: " + totalVotes;
    }


}
