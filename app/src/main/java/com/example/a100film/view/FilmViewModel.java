package com.example.a100film.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.a100film.filmRoom.Film;
import com.example.a100film.filmRoom.FilmRepository;

import java.util.List;

public class FilmViewModel extends AndroidViewModel {
    private FilmRepository repository;
    private LiveData<List<Film>> allFilms;
    private LiveData<List<Film>> getFilm;
    public FilmViewModel(@NonNull Application application) {
        super(application);
        repository= new FilmRepository(application);
        allFilms = repository.getAllFilms();
    }
    public void insert(Film film){
        repository.insert(film);
    }
    public void update(Film film){
        repository.update(film);
    }
    public void delete(Film film){
        repository.delete(film);
    }
    public void deleteAllFilms(){
        repository.deleteAllFilms();
    }
    public LiveData<List<Film>> getAllFilms(){
        return allFilms;
    }
    public LiveData<List<Film>> getFilm(String listNumber){
        getFilm = repository.getFilm(listNumber);
        return getFilm;
    }
}
