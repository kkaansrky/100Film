package com.example.a100film.filmRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insert(Film film);

    @Update
    void update(Film film);

    @Delete
    void delete(Film film);

    @Query("DELETE FROM film_table")
    void  deleteAllFilms();

    @Query("SELECT * FROM film_table")
    LiveData<List<Film>> getAllFilm();

    @Query("SELECT * FROM film_table WHERE listNumbers = :listNum")
    LiveData<List<Film>> getFilm(String listNum);

    @Query("SELECT COUNT(*) FROM film_table")
    long getCount();
}
