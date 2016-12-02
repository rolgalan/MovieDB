package io.rolgalan.moviedb.server;

import io.rolgalan.moviedb.server.model.SearchResponse;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public interface SearchResponseInterface {

    void onResultsReceived(SearchResponse response);

    void onError(String error);
}
