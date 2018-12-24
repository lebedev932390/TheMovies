package com.evgeny.lebedev.themovies.Model;

public class MovieAccountStates {
    private int id;
    private boolean favorite;
    private boolean watchlist;

    public int getId() {
        return id;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public boolean getWatchlist() {
        return watchlist;
    }
}
