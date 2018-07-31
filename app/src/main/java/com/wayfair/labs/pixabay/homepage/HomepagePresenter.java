package com.wayfair.labs.pixabay.homepage;

public class HomepagePresenter implements HomepageContract.Presenter {
    private HomepageContract.View view;

    HomepagePresenter(HomepageFragment view) {
        this.view = view;
    }

    @Override
    public void onSearch(String query) {
        if (query != null && !query.isEmpty()) {
            view.goToSearchPage(query);
        }
    }
}
