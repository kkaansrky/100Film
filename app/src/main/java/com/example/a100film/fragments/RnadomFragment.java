package com.example.a100film.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.a100film.R;

import java.util.Random;


public class RnadomFragment extends Fragment {

    final int max=100;
    final int min=1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rnadom,container,false);

        Button randButton = v.findViewById(R.id.randButton);
        final TextView txt = v.findViewById(R.id.textView);


        randButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




            }
        });



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rnadom, container, false);
    }
    public void randClick(View v){

        Log.d("TAG", "onClick:girdi ");
        Random rand = new Random();
        int randomNumber = rand.nextInt(max-min) + min;
        Log.d("TAG", "randClick: "+randomNumber);

    }
}