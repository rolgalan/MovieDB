package io.rolgalan.moviedb.server.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class MovieServer {
    @SerializedName("poster_path")
    String posterPath;
    boolean adult;
    String overview;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("genre_ids")
    int[] genreIds;
    int id;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("original_language")
    String originalLanguage;
    String title;
    @SerializedName("backdrop_path")
    String backdropPath;
    double popularity;
    @SerializedName("vote_count")
    int voteCount;
    String video;
    @SerializedName("vote_average")
    float voteAverage;


    String getposterPath() {
        return posterPath;
    }

    public boolean getadult() {
        return adult;
    }

    public String getoverview() {
        return overview;
    }

    public String getreleaseDate() {
        return releaseDate;
    }

    public int[] getgenreIds() {
        return genreIds;
    }

    public int getid() {
        return id;
    }

    public String getoriginalTitle() {
        return originalTitle;
    }

    public String getoriginalLanguage() {
        return originalLanguage;
    }

    public String gettitle() {
        return title;
    }

    public String getbackdropPath() {
        return backdropPath;
    }

    public double getpopularity() {
        return popularity;
    }

    public int getvoteCount() {
        return voteCount;
    }

    public String getvideo() {
        return video;
    }

    public float getvoteAverage() {
        return voteAverage;
    }
}
