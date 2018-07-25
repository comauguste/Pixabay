package com.wayfair.labs.pixabay.homepage;

import android.support.v4.app.FragmentManager;

public interface HomepageContract {
    interface View{
        FragmentManager getCustomFragmentManager();
    }

    interface Presenter{
        void onSearch(String query);
    }
}
