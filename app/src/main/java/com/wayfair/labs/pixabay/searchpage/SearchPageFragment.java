package com.wayfair.labs.pixabay.searchpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wayfair.labs.pixabay.R;
import com.wayfair.labs.pixabay.data.network.model.Photo;
import com.wayfair.labs.pixabay.searchpage.adapter.ImageGalleryAdapter;

import java.util.List;

public class SearchPageFragment extends Fragment implements SearchPageContract.View {
    public static final String SEARCH_PAGE_TAG = "searchPage";
    private String searchTerms;
    private RecyclerView recyclerView;
    private ImageButton backButton;
    private SearchView searchView;
    private ImageGalleryAdapter adapter;
    private ProgressBar progressBar;
    private SearchPageContract.Presenter presenter;


    public static SearchPageFragment newInstance(String searchTerms)
    {
        SearchPageFragment fragment = new SearchPageFragment();
        fragment.searchTerms = searchTerms;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);
        presenter = new SearchPagePresenter(this);

        recyclerView = view.findViewById(R.id.recycler_view);
        backButton = view.findViewById(R.id.back_button);
        progressBar = view.findViewById(R.id.progressBar);
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    presenter.fetchPhotos(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (searchTerms != null && !searchTerms.isEmpty()) {
            searchView.setQuery(searchTerms, false);
            presenter.fetchPhotos(searchTerms);
        }
    }

    @Override
    public void displayPhotos(List<Photo> photos) {
        adapter = new ImageGalleryAdapter(photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    @Override
    public void updateProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }
}
