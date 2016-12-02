package io.rolgalan.moviedb.data;

import io.rolgalan.moviedb.model.LinkedMovieList;
import io.rolgalan.moviedb.model.MovieList;
import io.rolgalan.moviedb.model.tmdb.TmdbMovie;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class DataProvider {
    public static final MovieList ITEMS = new LinkedMovieList();
    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            ITEMS.add(TmdbMovie.createFake(i));
        }
    }

}
