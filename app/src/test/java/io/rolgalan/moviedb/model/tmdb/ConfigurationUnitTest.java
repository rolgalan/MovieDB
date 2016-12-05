package io.rolgalan.moviedb.model.tmdb;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Roldán Galán on 05/12/2016.
 */
public class ConfigurationUnitTest {
    @Test
    public void extractDefaultSize() throws Exception {
        final String[] sizes = {"S1/", "S2/", "S3/"};
        Assert.assertEquals(sizes[1], Configuration.extractDefaultSize(sizes));
        Assert.assertEquals("", Configuration.extractDefaultSize(null));
        Assert.assertEquals("S1/", Configuration.extractDefaultSize(new String[]{"S1/"}));
    }

    @Test
    public void composeImageUrl() throws Exception {
        final String baseUrl = "http://baseurl/";
        final String imagePath = "imagePath";
        final String[] sizes = {"S1/", "S2/", "S3/"};

        String expected = baseUrl + sizes[1] + imagePath;
        Configuration configuration = new Configuration(baseUrl, sizes);
        Assert.assertEquals(expected, configuration.composeImageUrl(imagePath));

        expected = baseUrl + imagePath;
        configuration = new Configuration(baseUrl, null);
        Assert.assertEquals(expected, configuration.composeImageUrl(imagePath));

        final String[] size = {"S1/"};
        expected = baseUrl + size[0] + imagePath;
        configuration = new Configuration(baseUrl, size);
        Assert.assertEquals(expected, configuration.composeImageUrl(imagePath));
    }

}