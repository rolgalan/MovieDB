package io.rolgalan.moviedb.model;

import java.util.LinkedList;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class LinkedMovieList extends LinkedList<Movie> implements MovieList {
    private int page = 0;

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void incPage() {
        page++;
    }
}
