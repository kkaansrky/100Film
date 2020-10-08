package com.example.a100film.services;

import android.app.Application;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import com.example.a100film.filmRoom.Film;
import com.example.a100film.filmRoom.FilmDao;
import com.example.a100film.filmRoom.FilmDatabase;
import com.example.a100film.filmRoom.FilmRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class getFilmsFirebase {
    public void getFilmsFirebase(final Application application) {
        new Thread(new Runnable() {
            public void run() {
                FirebaseAuth auth= FirebaseAuth.getInstance();
                final FilmRepository filmRepository = new FilmRepository(application);

                if(filmRepository.getCount()<2) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("films");

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {




                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Film film = snapshot.getValue(Film.class);

                                filmRepository.insert(film);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        }).start();
    }
}