package io.rolgalan.moviedb.data;

import org.junit.Assert;
import org.junit.Test;

import io.rolgalan.moviedb.model.tmdb.Configuration;

/**
 * Created by Roldán Galán on 05/12/2016.
 */
public class DataProviderUnitTest {
    @Test
    public void composeImageUrl() throws Exception {
        final String baseUrl = "http://baseurl/";
        final String imagePath = "imagePath";
        final String[] sizes = {"S1/", "S2/", "S3/"};

        String expected = baseUrl + sizes[1] + imagePath;
        DataProvider.setConfiguration(new Configuration(baseUrl, sizes));
        Assert.assertEquals(expected, DataProvider.composeImageUrl(imagePath));

        expected = baseUrl + imagePath;
        DataProvider.setConfiguration(new Configuration(baseUrl, null));
        Assert.assertEquals(expected, DataProvider.composeImageUrl(imagePath));

        final String[] size = {"S1/"};
        expected = baseUrl + size[0] + imagePath;
        DataProvider.setConfiguration(new Configuration(baseUrl, size));
        Assert.assertEquals(expected, DataProvider.composeImageUrl(imagePath));
    }

}