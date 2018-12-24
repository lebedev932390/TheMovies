package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Image {
    private final String imageBaseUrlVertical = "https://image.tmdb.org/t/p/w500";
    private final String imageBaseUrlHorizontal = "https://image.tmdb.org/t/p/w533_and_h300_bestv2";

    @SerializedName("file_path")
    private String filePath;

    public String getFilePathVertical() {
        return imageBaseUrlVertical + filePath;
    }
    public String getFilePathHorizontal() {
        return imageBaseUrlHorizontal + filePath;
    }

}