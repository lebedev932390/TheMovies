package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Video {

    private final String baseLink = "Trailer: " + "https://www.youtube.com/watch?v=";
    private String site;
    private String type;
    @SerializedName("key")
    private String link;

    public boolean isYoutube() {
        if (site.equals("YouTube")){
            return true;
        }
        return false;
    }

    public boolean isTrailer() {
        if (type.equals("Trailer")){
            return true;
        }
        return false;
    }

    public String getLink() {
        return baseLink + link;
    }
}
