package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfPersonImages {

    @SerializedName("profiles")
    List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }
}
