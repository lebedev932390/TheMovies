package com.evgeny.lebedev.themovies.Presenter;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.PersonImagesList;
import com.evgeny.lebedev.themovies.Model.PersonMovieCreditsList;
import com.evgeny.lebedev.themovies.Model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonPresenter implements Contracts.Presenter.Person {

    private Contracts.View.Person view;
    private int personId;

    public PersonPresenter(Contracts.View.Person view, int personId) {
        this.view = view;
        this.personId = personId;

        getPersonDetails();
        getPersonCredits();
        getPersonImages();

    }

    private void getPersonImages(){
        App.getApi().getPersonImages(personId,App.apiKey)
                .enqueue(new Callback<PersonImagesList>() {
                    @Override
                    public void onResponse(Call<PersonImagesList> call, Response<PersonImagesList> response) {
                        if (response.isSuccessful()){
                            view.showImages(response.body().getImageList());
                        }
                    }

                    @Override
                    public void onFailure(Call<PersonImagesList> call, Throwable t) {

                    }
                });
    }

    private void getPersonCredits(){
        App.getApi().getPersonMovieCredits(personId,App.apiKey)
                .enqueue(new Callback<PersonMovieCreditsList>() {
                    @Override
                    public void onResponse(Call<PersonMovieCreditsList> call, Response<PersonMovieCreditsList> response) {
                        if (response.isSuccessful()){
                            view.showCredits(response.body().getCastList(),
                                    response.body().getCrewList());
                        }
                    }

                    @Override
                    public void onFailure(Call<PersonMovieCreditsList> call, Throwable t) {

                    }
                });
    }

    private void getPersonDetails(){
        App.getApi().getPersonDetails(personId,App.apiKey)
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        if (response.isSuccessful()){
                            view.showDetails(response.body().getProfilePathLarge(),
                                    response.body().getName(),
                                    response.body().getKnownForDepartment(),
                                    response.body().getBiography(),
                                    response.body().getPlaceOfBirth(),
                                    response.body().getBirthday(),
                                    response.body().getDeathday());

                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
    }
}
