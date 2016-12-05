package io.rolgalan.moviedb.server;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.rolgalan.moviedb.MainActivity;
import io.rolgalan.moviedb.server.model.ConfigurationResponse;
import io.rolgalan.moviedb.server.model.SearchResponse;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class RestClient {
    private final static String BASE_URL = "https://api.themoviedb.org/3/";
    private static ApiInterface apiInterface;

    public synchronized static ApiInterface getClient() {
        if (apiInterface == null) {
            final OkHttpClient clientAux = new OkHttpClient.Builder().build();

            OkHttpClient okClient = clientAux.newBuilder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(getApiKeyInterceptor())
                    .build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = client.create(ApiInterface.class);
        }

        return apiInterface;
    }

    private static Interceptor getApiKeyInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", "93aea0c77bc168d8bbce3918cefefa45")
                        .build();

                Request.Builder requestBuilder = original.newBuilder().url(url);

                Log.i(MainActivity.TAG, "Request url: " + url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    public interface ApiInterface {
        @GET("search/movie")
        Call<SearchResponse> search(@Query("query") String query, @Query("page") int page);

        @GET("discover/movie/?sort_by=popularity.desc")
        Call<SearchResponse> discover(@Query("page") int page);

        @GET("configuration")
        Call<ConfigurationResponse> configuration();

        //TODO Implement movie details @GET("movie/{id}") Call<MovieResponse> movie(@Path("id") int id);
    }
}
