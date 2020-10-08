package com.example.a100film.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a100film.R;
import com.example.a100film.activity.FilmDetay;
import com.example.a100film.activity.MainActivity;
import com.example.a100film.filmRoom.Film;
import com.example.a100film.view.FilmAdapter;
import com.example.a100film.view.FilmViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FilmFragment extends Fragment {


    FilmViewModel filmViewModel;
    List<Film> filmList = new ArrayList<Film>();
    FilmAdapter adapter;

    Context context;

    final int max=100;
    final int min=1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_film,container,false);

        context=getContext();

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new FilmAdapter(getContext());
        recyclerView.setAdapter(adapter);


        for (int i = 0;i<4;i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(max-min) + min;
            filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
            filmViewModel.getFilm(String.valueOf(randomNumber)).observe(this, new Observer<List<Film>>() {
                @Override
                public void onChanged(@Nullable List<Film> films) {
                    filmList.add(films.get(0));
                }
            });
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.submitList(filmList);
            }
        }, 2000);

        adapter.setOnItemClickListener(new FilmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Film film) {

                Intent i=new Intent(context, FilmDetay.class);
                i.putExtra("film_id", film.getListNumbers());
                i.putExtra("page_id", "1");
                context.startActivity(i);
            }
        });


        return v;
    }
    public void randomFilm(){


    }


}