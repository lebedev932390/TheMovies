package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfCredits {

    private int id;
    @SerializedName("cast")
    private List<Cast> listOfCast;
    @SerializedName("crew")
    private List<Crew> listOfCrew;

    public List<Crew> getListOfCrew() {
        return listOfCrew;
    }

    public List<Cast> getListOfCast() {
        return listOfCast;
    }
}
