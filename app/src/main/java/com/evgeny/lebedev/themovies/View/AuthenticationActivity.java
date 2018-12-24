package com.evgeny.lebedev.themovies.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Presenter.AuthenticationPresenter;
import com.evgeny.lebedev.themovies.R;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener, Contracts.View.Authentication {

    private Button buttonSignIn, buttonSignUp;
    private TextView textViewError;
    private EditText editTextUsername, editTextPassword;
    private CheckBox checkBoxRememberMe;
    private SharedPreferences sharedPreferences;
    private RelativeLayout authenticationSplashLayout;

    private Contracts.Presenter.Authentication presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        buttonSignIn = findViewById(R.id.authentication_sign_in_button);
        buttonSignIn.setOnClickListener(this);
        buttonSignUp = findViewById(R.id.authentication_sign_up_button);
        buttonSignUp.setOnClickListener(this);
        textViewError = findViewById(R.id.authentication_error_text_view);
        editTextUsername = findViewById(R.id.authentication_username_edit_text);
        editTextPassword = findViewById(R.id.authentication_password_edit_text);
        checkBoxRememberMe = findViewById(R.id.authentication_remember_me_checkbox);
        authenticationSplashLayout = findViewById(R.id.authentication_splash_layout);

        sharedPreferences = getSharedPreferences("authentication",MODE_PRIVATE);
        presenter = new AuthenticationPresenter(this, sharedPreferences);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.authentication_sign_in_button:
                presenter.buttonSignInClicked(editTextUsername.getText().toString(), editTextPassword.getText().toString(), checkBoxRememberMe.isChecked() );
                buttonSignUp.setClickable(false);
                buttonSignIn.setClickable(false);
                break;
            case R.id.authentication_sign_up_button:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/account/signup"));
                startActivity(browserIntent);
                break;

        }
    }

    @Override
    public void needAuthentication() {

        authenticationSplashLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        buttonSignUp.setClickable(true);
        buttonSignIn.setClickable(true);
        textViewError.setText(error);
    }

    @Override
    public void missionComplete() {
        startActivity(new Intent(this,DashboardActivity.class));
        finish();
    }
}

