package io.rolgalan.moviedb.server;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public interface ServerResponseInterface<T> {

    void onResultsReceived(T response);

    void onError(String error);
}
