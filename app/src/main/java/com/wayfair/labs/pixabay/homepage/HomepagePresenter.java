package com.wayfair.labs.pixabay.homepage;

import com.wayfair.labs.pixabay.searchpage.SearchPageFragment;
import com.wayfair.labs.pixabay.util.Navigator;

public class HomepagePresenter implements HomepageContract.Presenter {
    private Navigator navigator;
    private HomepageContract.View view;

    public HomepagePresenter(HomepageFragment view) {
        this.view = view;
        navigator = new Navigator(view.getCustomFragmentManager());
    }

    @Override
    public void onSearch(String query) {
        if (query != null && !query.isEmpty()) {
            navigator.navigate(SearchPageFragment.newInstance(query));
        }
    }
}
