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


    public String getPosterPath() {
        return posterPath;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }
}
