package com.wayfair.labs.pixabay.searchpage;

import com.wayfair.labs.pixabay.data.network.NetworkService;
import com.wayfair.labs.pixabay.data.network.PixabayAPI;
import com.wayfair.labs.pixabay.data.network.model.Photos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPageModel implements SearchPageContract.Model {
    private PixabayAPI service;
    private SearchPageContract.Presenter presenter;

    public SearchPageModel(SearchPagePresenter presenter) {
        service = NetworkService.create(PixabayAPI.class);
        this.presenter = presenter;

    }

    @Override
    public void retrievePhotos(String searchTerms) {
        Call<Photos> results = service.retrievePhotos(searchTerms);
        results.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.isSuccessful()) {
                    presenter.onPhotoRetrievedSuccesffuly(response.body().photos);
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                presenter.onPhotoRetrievedFailed(t);
            }
        });
    }
}
