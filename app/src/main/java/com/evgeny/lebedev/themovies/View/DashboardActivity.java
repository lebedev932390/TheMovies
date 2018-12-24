package com.evgeny.lebedev.themovies.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.evgeny.lebedev.themovies.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPopularMovies, buttonTopRatedMovies, buttonFavoriteMovies, buttonWatchlist, buttonSearchMovie, buttonSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        buttonPopularMovies = findViewById(R.id.dashboard_popular_movies_button);
        buttonPopularMovies.setOnClickListener(this);
        buttonTopRatedMovies = findViewById(R.id.dashboard_top_rated_movies_button);
        buttonTopRatedMovies.setOnClickListener(this);
        buttonFavoriteMovies = findViewById(R.id.dashboard_favorite_movies_button);
        buttonFavoriteMovies.setOnClickListener(this);
        buttonWatchlist = findViewById(R.id.dashboard_watchlist_button);
        buttonWatchlist.setOnClickListener(this);
        buttonSearchMovie = findViewById(R.id.dashboard_search_movie_button);
        buttonSearchMovie.setOnClickListener(this);
        buttonSignOut = findViewById(R.id.dashboard_sign_out_button);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("authentication",MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                startActivity(new Intent(DashboardActivity.this,AuthenticationActivity.class));
                DashboardActivity.this.finishAffinity();

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MovieListActivityList.class);

        switch (v.getId()) {
            case R.id.dashboard_popular_movies_button:
                intent.putExtra("listType", 1);
                break;
            case R.id.dashboard_top_rated_movies_button:
                intent.putExtra("listType", 2);
                break;
            case R.id.dashboard_favorite_movies_button:
                intent.putExtra("listType", 3);
                break;
            case R.id.dashboard_watchlist_button:
                intent.putExtra("listType", 4);
                break;
            case R.id.dashboard_search_movie_button:
                intent = new Intent(this,SearchMovieActivity.class);
                break;


        }
        startActivity(intent);
    }
}