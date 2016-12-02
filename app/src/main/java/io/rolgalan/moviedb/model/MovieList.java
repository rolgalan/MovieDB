package io.rolgalan.moviedb.model;


import java.util.List;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public interface MovieList extends List<Movie> {

    int getPage();

    void setPage(int p);
}
