package com.example.a100film.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.a100film.R;
import com.example.a100film.activity.StartActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {

    Button exitBT;
    TextView idProf,givenName,email;
    FirebaseUser fUser;
    ImageView image_profile;

    String personName,personGivenName,personFamilyName,personEmail,personId;
    Uri personPhoto;

    private GoogleSignInAccount acct;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_profile,container,false);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        idProf = v.findViewById(R.id.user_profile_name);
        image_profile = v.findViewById(R.id.user_profile_photo);
        givenName = v.findViewById(R.id.user_given_name);
        exitBT = v.findViewById(R.id.exitBt);
        email = v.findViewById(R.id.email);

        fUser = FirebaseAuth.getInstance().getCurrentUser();


        //*****************************************************
        acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
             personName = acct.getDisplayName();
             personGivenName = acct.getGivenName();
             personEmail = acct.getEmail();
             personPhoto = acct.getPhotoUrl();
        }
        //*******************************************************

        idProf.setText(personName);
        givenName.setText(personGivenName);
        email.setText(personEmail);
        Glide.with(this).load(personPhoto).into(image_profile);





        exitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                            }
                        });
                startActivity(new Intent(getActivity(), StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });



        return v;
    }
}