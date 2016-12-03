package io.rolgalan.moviedb.server.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Roldán Galán on 03/12/2016.
 */

public class ConfigurationResponse {
    ImagesServer images;
    @SerializedName("change_keys")
    String[] changeKeys;

    public ImagesServer getImages() {
        return images;
    }

    public String getBaseUrl() {
        return getImages() != null ? getImages().getBaseUrl() : null;
    }

    public String[] getPosterSizes() {
        return getImages() != null ? getImages().getPosterSizes() : null;
    }

    public static class ImagesServer {
        @SerializedName("base_url")
        String baseUrl;
        @SerializedName("secure_base_url")
        String secureBaseUrl;
        @SerializedName("logo_sizes")
        String[] logoSizes;
        @SerializedName("poster_sizes")
        String[] posterSizes;
        @SerializedName("profile_sizes")
        String[] profileSizes;
        @SerializedName("still_sizes")
        String[] stillSizes;


        public String getBaseUrl() {
            return baseUrl;
        }

        public String[] getPosterSizes() {
            return posterSizes;
        }
    }
}
