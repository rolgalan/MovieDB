package io.rolgalan.moviedb.model.tmdb;

import io.rolgalan.moviedb.model.Movie;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class TmdbMovie implements Movie {

    private final String title, releaseYear, overview, posterUrl;

    //TODO create constructor from TMDBMovie server

    public TmdbMovie(String title, String releaseYear, String overview, String posterUrl) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public static TmdbMovie createFake(int i) {
        return new TmdbMovie("Title " + i, String.valueOf((int) (1916 + Math.random() * 100)), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "http://pics.filmaffinity.com/westworld-284116982-large.jpg");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getPosterUrl() {
        return posterUrl;
    }
}
