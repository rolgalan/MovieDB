package io.rolgalan.moviedb.data;

import io.rolgalan.moviedb.model.LinkedMovieList;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;
import io.rolgalan.moviedb.model.tmdb.Configuration;
import io.rolgalan.moviedb.model.tmdb.TmdbMovie;
import io.rolgalan.moviedb.server.ApiManager;
import io.rolgalan.moviedb.server.ServerResponseInterface;
import io.rolgalan.moviedb.server.model.ConfigurationResponse;
import io.rolgalan.moviedb.server.model.MovieServer;
import io.rolgalan.moviedb.server.model.SearchResponse;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class DataProvider {
    public static final MovieList ITEMS = new LinkedMovieList();
    private static Configuration configuration;

    public static void discoverMovies(int page, DataInterface<MovieList> listener) {
        ApiManager.getInstance().discoverMovies(page, new SearchResponseBoundary(listener));
    }

    public static void searchMovies(String query, int page, DataInterface<MovieList> listener) {
        ApiManager.getInstance().searchMovies(query, page, new SearchResponseBoundary(listener));
    }

    public static void getConfiguration(DataInterface<Configuration> listener) {
        ApiManager.getInstance().getConfiguration(new ConfigurationResponseBoundary(listener));
    }

    public static Configuration getConfiguration() {
        if (configuration == null) getConfiguration(null);
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        DataProvider.configuration = configuration;
    }

    public static String composeImageUrl(String imagePath) {
        if (configuration != null) {
            return configuration.composeImageUrl(imagePath);
        }
        return null;
    }

    private static class SearchResponseBoundary implements ServerResponseInterface<SearchResponse> {
        final DataInterface<MovieList> listener;

        public SearchResponseBoundary(DataInterface<MovieList> listener) {
            this.listener = listener;
        }

        @Override
        public void onResultsReceived(SearchResponse response) {
            if (response != null && response.results != null && !response.results.isEmpty()) {
                MovieList list = new LinkedMovieList();
                for (MovieServer ms : response.results) {
                    Movie m = new TmdbMovie(ms);
                    if (m != null) list.add(m);
                }
                ITEMS.setPage(response.page);
                if (ITEMS.getPage() <= 1) ITEMS.clear();
                ITEMS.addAll(list);
                if (listener != null) listener.onResultsReceived(list);
            }
        }

        @Override
        public void onError(String error) {
            if (listener != null) listener.onError(error);
        }
    }

    private static class ConfigurationResponseBoundary implements ServerResponseInterface<ConfigurationResponse> {
        final DataInterface<Configuration> listener;

        public ConfigurationResponseBoundary(DataInterface<Configuration> listener) {
            this.listener = listener;
        }

        @Override
        public void onResultsReceived(ConfigurationResponse response) {
            if (response != null) {
                setConfiguration(new Configuration(response.getBaseUrl(), response.getPosterSizes()));
                if (listener != null) listener.onResultsReceived(configuration);
            }
        }

        @Override
        public void onError(String error) {
            if (listener != null) listener.onError(error);
        }
    }
}
