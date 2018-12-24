package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class BodyWatchlist {

    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("media_id")
    private Integer mediaId;
    @SerializedName("watchlist")
    private Boolean watchlist;

    public BodyWatchlist(String mediaType, Integer mediaId, Boolean watchlist) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
        this.watchlist = watchlist;
    }
}
