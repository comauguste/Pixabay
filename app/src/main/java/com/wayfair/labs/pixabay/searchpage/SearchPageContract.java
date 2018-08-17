package com.wayfair.labs.pixabay.searchpage;

import com.wayfair.labs.pixabay.data.network.model.Photo;

import java.util.List;

public interface SearchPageContract {

    interface View {
        void displayPhotos(List<Photo> photos);

        void updateProgressBarVisibility(int visibility);

        void showToastMessage(String message);

        void clearData();
    }

    interface Presenter {
        void fetchPhotos(final String searchTerms);

        void onPhotoRetrievedSuccesffuly(List<Photo> photos);

        void onPhotoRetrievedFailed(Throwable throwable);
    }

    interface Model {
        void retrievePhotos(final String searchTerms);
    }
}
