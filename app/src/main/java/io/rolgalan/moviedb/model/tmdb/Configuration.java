package io.rolgalan.moviedb.model.tmdb;

/**
 * Created by Roldán Galán on 03/12/2016.
 */

public class Configuration {
    private final String baseImagesUrl;
    private final String[] posterSizes;

    public Configuration(String baseImagesUrl, String[] posterSizes) {
        this.baseImagesUrl = baseImagesUrl;
        this.posterSizes = posterSizes;
    }

    public String getBaseImagesUrl() {
        return baseImagesUrl;
    }

    public String[] getPosterSizes() {
        return posterSizes;
    }

    public String composeImageUrl(String imgPath){
        return baseImagesUrl + posterSizes[1] + imgPath;
    }
}
