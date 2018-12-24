package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCreditsList {

    private int id;
    @SerializedName("cast")
    private List<Cast> castList;
    @SerializedName("crew")
    private List<Crew> crewList;

    public List<Crew> getCrewList() {
        return crewList;
    }

    public List<Cast> getCastList() {
        return castList;
    }
}
