package com.wayfair.labs.pixabay.homepage;

public interface HomepageContract {
    interface View {
        void goToSearchPage(String query);
    }

    interface Presenter {
        void onSearch(String query);
    }
}
