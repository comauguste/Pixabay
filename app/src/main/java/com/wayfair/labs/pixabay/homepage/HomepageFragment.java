package com.wayfair.labs.pixabay.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayfair.labs.pixabay.R;
import com.wayfair.labs.pixabay.searchpage.SearchPageFragment;

import static com.wayfair.labs.pixabay.searchpage.SearchPageFragment.SEARCH_PAGE_TAG;

public class HomepageFragment extends Fragment implements HomepageContract.View {

    private SearchView searchView;
    private HomepageContract.Presenter presenter;

    public static Fragment newInstance() {
        HomepageFragment fragment = new HomepageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        presenter = new HomepagePresenter(this);

        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return view;
    }

    @Override
    public void goToSearchPage(String query) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchPageFragment.newInstance(query))
                .addToBackStack(SEARCH_PAGE_TAG)
                .commit();
    }
}
