package com.evgeny.lebedev.themovies.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evgeny.lebedev.themovies.App;
import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.Cast;
import com.evgeny.lebedev.themovies.Model.Crew;
import com.evgeny.lebedev.themovies.Model.Image;
import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.Presenter.MoviePresenter;
import com.evgeny.lebedev.themovies.R;
import com.evgeny.lebedev.themovies.View.Adapter.CreditAdapter;
import com.evgeny.lebedev.themovies.View.Adapter.ImageAdapter;
import com.evgeny.lebedev.themovies.View.Adapter.MovieAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MovieActivity extends AppCompatActivity implements Contracts.View.ChosenMovie, View.OnClickListener {

    private TextView textViewTitle, textViewTagline, textViewReleaseDate, textViewRuntime, textViewOverview, textViewBudget, textViewRevenue, textViewRating, textViewHomepage, textViewTrailer;
    private ImageView imageViewPoster;
    private CheckBox checkBoxFavorite, checkBoxWatchlist;
    private Contracts.Presenter.ChosenMovie presenter;

    private RecyclerView recyclerViewRecommended;
    private MovieAdapter adapterRecommended;
    private LinearLayoutManager layoutManagerRecommended;
    private Context context;

    private RecyclerView recyclerViewCast;
    private CreditAdapter adapterCast;
    private LinearLayoutManager layoutManagerCast;

    private RecyclerView recyclerViewCrew;
    private CreditAdapter adapterCrew;
    private LinearLayoutManager layoutManagerCrew;

    private RecyclerView recyclerViewBackdrops;
    private ImageAdapter adapterBackdrop;
    private LinearLayoutManager layoutManagerBackdrops;

    private static final int animationTime = 1500;

    RingProgressBar ringProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        textViewTitle = findViewById(R.id.movie_title_text_view);
        textViewTagline = findViewById(R.id.movie_tagline_text_view);
        textViewReleaseDate = findViewById(R.id.movie_release_date);
        textViewRuntime = findViewById(R.id.movie_runtime);
        textViewOverview = findViewById(R.id.movie_overview_text_view);
        textViewBudget = findViewById(R.id.movie_budget_text_view);
        textViewRevenue = findViewById(R.id.movie_revenue_text_view);
        textViewRating = findViewById(R.id.movie_rating_text_view);
        textViewHomepage = findViewById(R.id.movie_homepage_text_view);
        textViewTrailer = findViewById(R.id.movie_trailer_text_view);

        imageViewPoster = findViewById(R.id.movie_poster_image_view);
        checkBoxFavorite = findViewById(R.id.movie_favorites_checkbox);
        checkBoxFavorite.setOnClickListener(this);
        checkBoxWatchlist = findViewById(R.id.movie__watchlist_checkbox);
        checkBoxWatchlist.setOnClickListener(this);

        ringProgressBar = findViewById(R.id.progress_bar);


        Toast.makeText(this, Integer.toString(getIntent().getIntExtra("id", 557)), Toast.LENGTH_LONG).show();

        presenter = new MoviePresenter(this, getIntent().getIntExtra("id", 557));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_favorites_checkbox:
                Toast.makeText(this, App.sessionId, Toast.LENGTH_SHORT).show();
                presenter.addToFavorites(checkBoxFavorite.isChecked());


                break;
            case R.id.movie__watchlist_checkbox:
                presenter.addToWatchList(checkBoxWatchlist.isChecked());


                break;
        }
    }


    @Override
    public void showMovieInfo(String title,
                              String tagline,
                              String overview,
                              int rating,
                              String posterUrl,
                              String homepage,
                              String budget,
                              String revenue,
                              String runtime,
                              String releaseDate) {

        textViewTitle.setText(title);
        textViewTagline.setText(tagline);

        textViewOverview.setText(overview);
        textViewReleaseDate.setText("Release date: " + releaseDate);
        textViewRuntime.setText(runtime);
        textViewBudget.setText(budget);
        textViewRevenue.setText(revenue);
        textViewHomepage.setText(homepage);


        Picasso.get().load(posterUrl)
                .error(getResources().getDrawable(R.drawable.placeholder_vertical))
                .into(imageViewPoster);

        ringProgressBar(rating);


    }

    private class myHandler extends Handler {
        int progress;
        int finalProgress;

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public void setFinalProgress(int finalProgress) {
            this.finalProgress = finalProgress;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (progress < finalProgress) {
                    progress++;
                    ringProgressBar.setProgress(progress);
                }
            }
        }


    }

    private void ringProgressBar(final int finalProgress) {


        final myHandler myHandler = new myHandler();
        myHandler.setFinalProgress(finalProgress);
        myHandler.setProgress(0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < finalProgress; i++) {
                    try {
                        Thread.sleep(animationTime / finalProgress);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, finalProgress);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewRating.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setDuration(animationTime);
        animator.start();
    }

    @Override
    public void showMovieTrailer(String trailer) {
        textViewTrailer.setText(trailer);

    }

    @Override
    public void showMovieAccountStates(boolean favorite, boolean watchlist) {
        checkBoxFavorite.setChecked(favorite);
        checkBoxFavorite.setClickable(true);

        checkBoxWatchlist.setChecked(watchlist);
        checkBoxWatchlist.setClickable(true);
    }

    @Override
    public void showMovieCredits(final List<Cast> castList, final List<Crew> crewList) {


        adapterCast = new CreditAdapter(castList,true);
        recyclerViewCast = findViewById(R.id.movie_cast_recyclerview);
        recyclerViewCast.setHasFixedSize(true);
        layoutManagerCast = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCast.setLayoutManager(layoutManagerCast);
        recyclerViewCast.setAdapter(adapterCast);
        adapterCast.setOnClickListener(new CreditAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MovieActivity.this, PersonActivity.class);
                intent.putExtra("id", castList.get(position).getId());
                startActivity(intent);
                Toast.makeText(MovieActivity.this, Integer.toString(castList.get(position).getId()), Toast.LENGTH_SHORT).show();
            }
        });


        adapterCrew = new CreditAdapter(crewList,false);
        recyclerViewCrew = findViewById(R.id.movie_crew_recyclerview);
        recyclerViewCrew.setHasFixedSize(true);
        layoutManagerCrew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCrew.setLayoutManager(layoutManagerCrew);
        recyclerViewCrew.setAdapter(adapterCrew);
        adapterCrew.setOnClickListener(new CreditAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MovieActivity.this, PersonActivity.class);
                intent.putExtra("id", crewList.get(position).getId());
                startActivity(intent);

                Toast.makeText(MovieActivity.this, Integer.toString(crewList.get(position).getId()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showRecommendedMovies(final List<Movie> moviesList) {
        Log.e("recommended", Integer.toString(moviesList.size()));

        adapterRecommended = new MovieAdapter(moviesList,  false);
        recyclerViewRecommended = findViewById(R.id.movie_recommended_movies_recyclerview);
        recyclerViewRecommended.setHasFixedSize(true);
        layoutManagerRecommended = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommended.setLayoutManager(layoutManagerRecommended);
        recyclerViewRecommended.setAdapter(adapterRecommended);
        adapterRecommended.setOnClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MovieActivity.this, MovieActivity.class);
                intent.putExtra("id", moviesList.get(position).getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void showMovieImages(List<Image> imagesList) {
        adapterBackdrop = new ImageAdapter(imagesList,false);
        recyclerViewBackdrops = findViewById(R.id.movie_backdrops_recyclerview);
        recyclerViewBackdrops.setHasFixedSize(true);
        layoutManagerBackdrops = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBackdrops.setLayoutManager(layoutManagerBackdrops);
        recyclerViewBackdrops.setAdapter(adapterBackdrop);

    }

}

