package com.example.a100film.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.a100film.R;
import com.example.a100film.activity.FilmDetay;
import com.example.a100film.activity.MainActivity;
import com.example.a100film.filmRoom.Film;
import com.example.a100film.view.FilmAdapter;
import com.example.a100film.view.FilmViewModel;

import java.util.List;


public class ListFragment extends Fragment {

    private FilmViewModel filmViewModel;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_list,container,false);

        context = getContext();

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);


        final FilmAdapter adapter = new FilmAdapter(getContext());
        recyclerView.setAdapter(adapter);


        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        filmViewModel.getAllFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> films) {
                adapter.submitList(films);
            }
        });

        adapter.setOnItemClickListener(new FilmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Film film) {

                Intent i=new Intent(context, FilmDetay.class);
                i.putExtra("film_id", film.getListNumbers());
                i.putExtra("page_id", "0");
                context.startActivity(i);
            }
        });


        return v;
    }
}