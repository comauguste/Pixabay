package com.wayfair.labs.pixabay;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SearchPageFragment extends Fragment {
    private String searchTerms;
    private RecyclerView recyclerView;
    private ImageButton backButton;
    private SearchView searchView;
    private ImageGalleryAdapter adapter;
    private ProgressBar progressBar;


    public static SearchPageFragment newInstance(String searchTerms) {
        SearchPageFragment fragment = new SearchPageFragment();
        fragment.searchTerms = searchTerms;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        backButton = view.findViewById(R.id.back_button);
        progressBar = view.findViewById(R.id.progressBar);
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    fetchPhotos(query);

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
                goBack();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (searchTerms != null && !searchTerms.isEmpty()) {
            searchView.setQuery(searchTerms, false);
            fetchPhotos(searchTerms);
        }
    }

    private void fetchPhotos(final String searchTerms) {
        if (adapter != null) {
            adapter.clear();
        }
        updateProgressBarVisibility(View.VISIBLE);
        PixabayAPI service = NetworkService.create(PixabayAPI.class);
        Call<Photos> results = service.retrievePhotos(searchTerms);
        results.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.isSuccessful()) {
                    updateProgressBarVisibility(View.GONE);
                    List<Photo> photos = response.body().photos;
                    if (photos.size() >= 1) {
                        displayPhotos(photos);
                    } else {
                        showToastMessage(String.format("No image found for \" %s \" ", searchTerms));
                    }
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    private void displayPhotos(List<Photo> photos) {
        adapter = new ImageGalleryAdapter(photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    private void goBack() {
        getFragmentManager().popBackStack();
    }

    private void updateProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    private void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
