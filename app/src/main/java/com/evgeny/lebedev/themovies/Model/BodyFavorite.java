package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class BodyFavorite {
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("media_id")
    private Integer mediaId;
    @SerializedName("favorite")
    private Boolean favorite;

    public BodyFavorite(String mediaType, Integer mediaId, Boolean favorite) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
        this.favorite = favorite;
    }
}
