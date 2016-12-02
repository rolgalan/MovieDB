package io.rolgalan.moviedb.data;

import io.rolgalan.moviedb.model.LinkedMovieList;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;
import io.rolgalan.moviedb.model.tmdb.TmdbMovie;
import io.rolgalan.moviedb.server.ApiManager;
import io.rolgalan.moviedb.server.SearchResponseInterface;
import io.rolgalan.moviedb.server.model.MovieServer;
import io.rolgalan.moviedb.server.model.SearchResponse;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class DataProvider {
    public static final MovieList ITEMS = new LinkedMovieList();

    public static void discoverMovies(DataListInterface listener) {
        ApiManager.getInstance().discoverMovies(new SearchResponseBoundary(listener));
    }

    public static void searchMovies(String query, DataListInterface listener) {
        ApiManager.getInstance().searchMovies(query, new SearchResponseBoundary(listener));
    }

    private static class SearchResponseBoundary implements SearchResponseInterface {
        final DataListInterface listener;

        public SearchResponseBoundary(DataListInterface listener) {

            this.listener = listener;
        }

        @Override
        public void onResultsReceived(SearchResponse response) {
            if (response != null && response.results != null && !response.results.isEmpty()) {
                MovieList list = new LinkedMovieList();
                list.setPage(response.page);
                for (MovieServer ms : response.results) {
                    Movie m = new TmdbMovie(ms);
                    if (m != null) list.add(m);
                }
                ITEMS.clear();
                ITEMS.addAll(list);
                listener.onResultsReceived(list);
            }
        }

        @Override
        public void onError(String error) {
            listener.onError(error);
        }
    }
}
