package io.rolgalan.moviedb.model.tmdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Roldán Galán on 05/12/2016.
 */
public class TmdbMovieUnitTest {
    @Test
    public void getYearFromDate() throws Exception {
        assertEquals("2004", TmdbMovie.getYearFromDate("2004/03/12"));
        assertEquals("2010", TmdbMovie.getYearFromDate("2010-05-11"));

        //Checks that doesn't crash for wrong data
        assertEquals("", TmdbMovie.getYearFromDate(null));
        assertEquals("", TmdbMovie.getYearFromDate(""));
        assertEquals("", TmdbMovie.getYearFromDate("123"));
    }

}