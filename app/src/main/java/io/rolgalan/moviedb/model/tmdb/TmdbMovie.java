package io.rolgalan.moviedb.model.tmdb;

import io.rolgalan.moviedb.data.DataProvider;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.server.model.MovieServer;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class TmdbMovie implements Movie {

    private final String title, releaseYear, overview, posterUrl;

    public TmdbMovie(String title, String releaseYear, String overview, String posterUrl) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public TmdbMovie(MovieServer ms) {
        this(ms.getOriginalTitle(), getYearFromDate(ms.getReleaseDate()), ms.getOverview(), ms.getPosterPath());
    }

    public static TmdbMovie createFake(int i) {
        return new TmdbMovie("Title " + i, String.valueOf((int) (1916 + Math.random() * 100)), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "http://pics.filmaffinity.com/westworld-284116982-large.jpg");
    }

    protected static String getYearFromDate(String date) {
        if (date != null && date.length() > 3) {
            return date.substring(0, 4);
        }
        return "";
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
        return DataProvider.composeImageUrl(posterUrl);
    }
}
