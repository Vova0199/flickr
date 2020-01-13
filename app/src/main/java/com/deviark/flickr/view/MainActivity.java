package com.deviark.flickr.view;

import android.os.Bundle;

import com.deviark.flickr.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.action_main:
                        loadFragment(PopularFragment.newInstance());
                        return true;
                    case R.id.action_favorites:
                        loadFragment(FavouriteFragment.newInstance());
                        return true;
                }
                return false;
            };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(PopularFragment.newInstance());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}

