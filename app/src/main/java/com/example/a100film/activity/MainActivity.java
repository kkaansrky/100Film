package com.example.a100film.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.a100film.R;
import com.example.a100film.fragments.BlankFragment;
import com.example.a100film.fragments.FilmFragment;
import com.example.a100film.fragments.ListFragment;
import com.example.a100film.fragments.ProfileFragment;
import com.example.a100film.fragments.RnadomFragment;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.vPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);






    }
    public void setCurrentItem (int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] childFragments;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            childFragments = new Fragment[]{
                    new ListFragment(), //0
                    new FilmFragment(), //1
                    new ProfileFragment() //2
            };
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new ListFragment(); //ChildFragment1 at position 0
                case 1:
                    return new FilmFragment(); //ChildFragment2 at position 1
                case 2:
                    return new ProfileFragment(); //ChildFragment3 at position 2
            }
            return null; //does not happen
        }

        @Override
        public int getCount() {
            return 3; //three fragments
        }

        // Ctrl + O

        @Override
        public CharSequence getPageTitle(int position) {
            String title = getItem(position).getClass().getName();
            return title.subSequence(title.lastIndexOf(".") + 1, title.length());
        }
    }

}