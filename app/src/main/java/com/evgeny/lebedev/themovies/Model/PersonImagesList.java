package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonImagesList {

    @SerializedName("profiles")
    private List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }
}
