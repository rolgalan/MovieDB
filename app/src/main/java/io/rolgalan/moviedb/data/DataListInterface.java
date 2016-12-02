package io.rolgalan.moviedb.data;

import io.rolgalan.moviedb.model.MovieList;

/**
 * Created by Roldán Galán on 02/12/2016.
 */
public interface DataListInterface {
    void onResultsReceived(MovieList list);
    void onError(String error);
}
