package com.example.a100film.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a100film.R;
import com.example.a100film.filmRoom.Film;
import com.example.a100film.view.FilmViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FilmDetay extends AppCompatActivity {


    ImageView filmImg;
    TextView filmName,filmNameEng,filmDetail;
    String idNumber,pageId;
    FilmViewModel filmViewModel;
    FloatingActionButton floatingactionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detay);


        //tanımlamalar
        filmImg=findViewById(R.id.film_profile_photo);
        filmName=findViewById(R.id.film_name);
        filmNameEng=findViewById(R.id.film_name_eng);
        filmDetail=findViewById(R.id.film_detail);
        floatingactionbutton=findViewById(R.id.fab);

        Intent intent = getIntent();
        idNumber = intent.getStringExtra("film_id");
        pageId = intent.getStringExtra("page_id");

        fillFilmDetail(idNumber);


        floatingactionbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               finish();
            }
        });



    }
    public void fillFilmDetail(String randomNumber){
        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        filmViewModel.getFilm(randomNumber).observe(this,new Observer<List<Film>>() {

            @Override
            public void onChanged(List<Film> films) {
                filmName.setText(films.get(0).getName());
                filmNameEng.setText(films.get(0).getNameEng());
                filmDetail.setText(films.get(0).getDetail());
                Glide.with(getApplicationContext()).load(films.get(0).getNimage()).into(filmImg);
            }
        });


    }
}