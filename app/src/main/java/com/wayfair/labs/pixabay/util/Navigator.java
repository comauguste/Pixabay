package com.wayfair.labs.pixabay.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wayfair.labs.pixabay.R;

public class Navigator {
    private FragmentManager fragmentManager;

    public Navigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    public void navigate(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("search_page")
                .commit();
    }

    public void goBack() {
        fragmentManager.popBackStack();
    }
}
