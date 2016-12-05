package io.rolgalan.moviedb.model.tmdb;

/**
 * Created by Roldán Galán on 03/12/2016.
 */

public class Configuration {
    private final String baseImagesUrl;
    private final String[] posterSizes;
    private final String defaultSize;

    public Configuration(String baseImagesUrl, String[] posterSizes) {
        this.baseImagesUrl = baseImagesUrl;
        this.posterSizes = posterSizes;
        defaultSize = extractDefaultSize(posterSizes);
    }

    /**
     * Safely set a default size from the available poster sizes array.
     * For a phone device we don't need a big image, but smalles one would be too small,
     * so we choose the second smallest size (position 1 in the array).
     * <p>
     * However, we don't know how many sizes are available in the API, so this
     * method could use the zero position if there is just one result.
     *
     * @param posterSizes
     * @return
     */
    protected static String extractDefaultSize(String[] posterSizes) {
        if (posterSizes != null && posterSizes.length > 0) {
            if (posterSizes.length > 1) return posterSizes[1];
            return posterSizes[0];
        }
        return "";
    }

    public String getBaseImagesUrl() {
        return baseImagesUrl;
    }

    public String[] getPosterSizes() {
        return posterSizes;
    }

    public String composeImageUrl(String imgPath) {
        return baseImagesUrl + defaultSize + imgPath;
    }
}
