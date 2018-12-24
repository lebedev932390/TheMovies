package com.evgeny.lebedev.themovies.Presenter;

import android.content.SharedPreferences;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.RequestToken;
import com.evgeny.lebedev.themovies.Model.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationPresenter implements Contracts.Presenter.Authentication {

    private Contracts.View.Authentication view;
    private SharedPreferences sharedPreferences;

    public AuthenticationPresenter(Contracts.View.Authentication view, SharedPreferences sharedPreferences) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        if (username == null || password == null) {
            view.needAuthentication();
        } else {
            buttonSignInClicked(username, password, false);
        }
    }


    private void getRequestToken(final String username, final String password) {
        App.getApi().getRequestToken(App.apiKey)
                .enqueue(new Callback<RequestToken>() {
                    @Override
                    public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                        if (response.isSuccessful()) {
                            createSessionWithLogin(username, password, response.body().getRequestToken());

                        } else {
                            view.needAuthentication();
                            view.showError("Something went wrong. Please, try later");
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestToken> call, Throwable t) {
                        view.needAuthentication();

                        view.showError("Check your internet connection.");
                    }
                });
    }

    private void createSessionWithLogin(String username, String password, final String requestToken) {

        App.getApi().createSessionWithLogin(username, password, requestToken, App.apiKey)
                .enqueue(new Callback<RequestToken>() {
                    @Override
                    public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                        if (response.isSuccessful()) {
                            createSession(response.body().getRequestToken());
                        } else if (response.code() == 401) {
                            view.needAuthentication();

                            view.showError("Invalid username and/or password.");

                        } else {
                            view.needAuthentication();
                            view.showError("Something went wrong. Please, try later");
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestToken> call, Throwable t) {
                        view.needAuthentication();

                        view.showError("Check your internet connection.");
                    }
                });
    }

    private void createSession(final String requestToken) {

        App.getApi().createSession(requestToken, App.apiKey)
                .enqueue(new Callback<Session>() {
                    @Override
                    public void onResponse(Call<Session> call, Response<Session> response) {
                        if (response.isSuccessful()) {
                            App.sessionId = response.body().getSessionId();
                            view.missionComplete();
                        } else {
                            view.needAuthentication();
                            view.showError("Something went wrong. Please, try later");
                        }
                    }

                    @Override
                    public void onFailure(Call<Session> call, Throwable t) {
                        view.needAuthentication();
                        view.showError("Check your internet connection.");
                    }
                });
    }

    @Override
    public void buttonSignInClicked(String username, String password, boolean rememberMe) {
        if (rememberMe) {
            saveUsernamePassword(username, password);
        }
        getRequestToken(username, password);
    }

    private void saveUsernamePassword(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }
}
