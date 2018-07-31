package com.wayfair.labs.pixabay.searchpage;

import android.view.View;

import com.wayfair.labs.pixabay.data.network.model.Photo;

import java.util.List;

import timber.log.Timber;

public class SearchPagePresenter implements SearchPageContract.Presenter {
    private SearchPageContract.View view;
    private SearchPageContract.Model model;
    private String searchTerms;

    SearchPagePresenter(SearchPageFragment view) {
        this.view = view;
        this.model = new SearchPageModel(this);
        searchTerms = "";
    }

    @Override
    public void fetchPhotos(String searchTerms) {
        view.clearData();
        view.updateProgressBarVisibility(View.VISIBLE);
        this.searchTerms = searchTerms;
        model.retrievePhotos(searchTerms);
    }

    @Override
    public void onPhotoRetrievedSuccesffuly(List<Photo> photos) {
        view.updateProgressBarVisibility(View.GONE);
        if (photos.size() >= 1) {
            view.displayPhotos(photos);
        } else {
            view.showToastMessage(String.format("No image found for \" %s \" ", searchTerms));
        }
    }

    @Override
    public void onPhotoRetrievedFailed(Throwable throwable) {
        Timber.e(throwable);
    }
}
