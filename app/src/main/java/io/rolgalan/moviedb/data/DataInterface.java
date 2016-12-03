package io.rolgalan.moviedb.data;

/**
 * Created by Roldán Galán on 02/12/2016.
 */
public interface DataInterface<T> {
    void onResultsReceived(T list);

    void onError(String error);
}
