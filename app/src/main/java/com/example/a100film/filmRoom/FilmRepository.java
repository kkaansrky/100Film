package com.example.a100film.filmRoom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class FilmRepository {
    private FilmDao filmDao;
    private LiveData<List<Film>> allFilms;
    private LiveData<List<Film>> getFilm;

    public FilmRepository(Application application) {
        FilmDatabase database = FilmDatabase.getInstance(application);
        filmDao = database.filmDao();
        allFilms = filmDao.getAllFilm();

    }
    public void insert(Film film){
        new InsertFilmAsyncTask(filmDao).execute(film);
    }
    public void update(Film film){ new UpdateFilmAsyncTask(filmDao).execute(film); }
    public void delete(Film film){
        new DeleteFilmAsyncTask(filmDao).execute(film);
    }
    public void deleteAllFilms(){ new DeleteAllFilmAsyncTask(filmDao).execute(); }
    public long getCount(){
        long count = filmDao.getCount();
        return count;
    }
    public LiveData<List<Film>> getFilm(String listNumber){
        getFilm = filmDao.getFilm(listNumber);
        return getFilm;
    }
    public LiveData<List<Film>> getAllFilms(){
        return allFilms;
    }



    private static class InsertFilmAsyncTask extends AsyncTask<Film,Void,Void>{
        private  FilmDao filmDao;

        private InsertFilmAsyncTask(FilmDao filmDao){
            this.filmDao=filmDao;
        }



        @Override
        protected Void doInBackground(Film... films) {
            filmDao.insert(films[0]);
            return null;
        }
    }
    private static class UpdateFilmAsyncTask extends AsyncTask<Film,Void,Void>{
        private  FilmDao filmDao;

        private UpdateFilmAsyncTask(FilmDao filmDao){
            this.filmDao=filmDao;
        }

        @Override
        protected Void doInBackground(Film... films) {
            filmDao.update(films[0]);
            return null;
        }
    }
    private static class DeleteFilmAsyncTask extends AsyncTask<Film,Void,Void>{
        private  FilmDao filmDao;

        private DeleteFilmAsyncTask(FilmDao filmDao){
            this.filmDao=filmDao;
        }

        @Override
        protected Void doInBackground(Film... films) {
            filmDao.delete(films[0]);
            return null;
        }
    }
    private static class DeleteAllFilmAsyncTask extends AsyncTask<Film,Void,Void>{
        private  FilmDao filmDao;

        private DeleteAllFilmAsyncTask(FilmDao filmDao){
            this.filmDao=filmDao;
        }

        @Override
        protected Void doInBackground(Film... films) {
            filmDao.deleteAllFilms();
            return null;
        }
    }
}
