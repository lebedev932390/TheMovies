package com.evgeny.lebedev.themovies.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeny.lebedev.themovies.Contracts;
import com.evgeny.lebedev.themovies.Model.Image;
import com.evgeny.lebedev.themovies.Model.Movie;
import com.evgeny.lebedev.themovies.Presenter.PersonPresenter;
import com.evgeny.lebedev.themovies.R;
import com.evgeny.lebedev.themovies.View.Adapter.ImageAdapter;
import com.evgeny.lebedev.themovies.View.Adapter.MovieAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonActivity extends AppCompatActivity implements Contracts.View.Person {

    private ImageView imageViewProfilePath;
    private TextView textViewName, textViewDepartment, textViewBiography, textViewPlaceOfBirth, textViewBirthDay, textViewDeathDay;

    private Contracts.Presenter.Person presenter;

    private RecyclerView recyclerViewCast;
    private MovieAdapter adapterCast;
    private LinearLayoutManager layoutManagerCast;

    private RecyclerView recyclerViewCrew;
    private MovieAdapter adapterCrew;
    private LinearLayoutManager layoutManagerCrew;

    private RecyclerView recyclerViewProfiles;
    private ImageAdapter adapterProfiles;
    private LinearLayoutManager layoutManagerProfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        imageViewProfilePath = findViewById(R.id.person_profile_path_image_view);
        textViewName = findViewById(R.id.person_name_text_view);
        textViewDepartment = findViewById(R.id.person_department_text_view);
        textViewBiography = findViewById(R.id.person_biography_text_view);
        textViewPlaceOfBirth = findViewById(R.id.person_place_of_birth_text_view);
        textViewBirthDay = findViewById(R.id.person_birthday_text_view);
        textViewDeathDay = findViewById(R.id.person_deathday_text_view);

        presenter = new PersonPresenter(this, getIntent().getIntExtra("id", 0));
    }

    @Override
    public void showDetails(String profilePath, String name, String department, String biography, String placeOfBirth, String birthday, String deathday) {
        Picasso.get().load(profilePath)
                .placeholder(R.drawable.placeholder_vertical)
                .into(imageViewProfilePath);
        textViewName.setText(name);
        textViewDepartment.setText(department);
        textViewBiography.setText(biography);
        textViewPlaceOfBirth.setText(placeOfBirth);
        textViewBirthDay.setText(birthday);
        textViewDeathDay.setText(deathday);
    }

    @Override
    public void showCredits(final List<Movie> castList, final List<Movie> crewList) {

        adapterCast = new MovieAdapter(castList, false);
        recyclerViewCast = findViewById(R.id.person_cast_recyclerview);
        recyclerViewCast.setHasFixedSize(true);
        layoutManagerCast = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCast.setLayoutManager(layoutManagerCast);
        recyclerViewCast.setAdapter(adapterCast);
        adapterCast.setOnClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PersonActivity.this, MovieActivity.class);
                intent.putExtra("id", castList.get(position).getId());
                startActivity(intent);
            }
        });


        adapterCrew = new MovieAdapter(crewList, false);
        recyclerViewCrew = findViewById(R.id.person_crew_recyclerview);
        recyclerViewCrew.setHasFixedSize(true);
        layoutManagerCrew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCrew.setLayoutManager(layoutManagerCrew);
        recyclerViewCrew.setAdapter(adapterCrew);
        adapterCrew.setOnClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PersonActivity.this, MovieActivity.class);
                intent.putExtra("id", crewList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showImages(List<Image> imageList) {
        adapterProfiles = new ImageAdapter(imageList, true);
        recyclerViewProfiles = findViewById(R.id.person_images_recyclerview);
        recyclerViewProfiles.setHasFixedSize(true);
        layoutManagerProfiles = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewProfiles.setLayoutManager(layoutManagerProfiles);
        recyclerViewProfiles.setAdapter(adapterProfiles);
    }
}
