package io.rolgalan.moviedb.server;

import android.util.Log;

import io.rolgalan.moviedb.MainActivity;
import io.rolgalan.moviedb.server.model.ConfigurationResponse;
import io.rolgalan.moviedb.server.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roldán Galán on 02/12/2016.
 */

public class ApiManager {
    private static ApiManager instance;

    private ApiManager() {
    }

    public static synchronized ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public void searchMovies(String query, final ServerResponseInterface listener) {
        Call<SearchResponse> call = RestClient.getClient().search(query);
        call.enqueue(new MyCallback(listener));
    }

    public void discoverMovies(final ServerResponseInterface listener) {
        Call<SearchResponse> call = RestClient.getClient().discover();
        call.enqueue(new MyCallback(listener));
    }

    public void getConfiguration(final ServerResponseInterface listener) {
        Call<ConfigurationResponse> call = RestClient.getClient().configuration();
        call.enqueue(new MyCallback(listener));
    }

    /**
     * Generic callback for common error handling.
     */
    private static class MyCallback<T> implements Callback<T> {
        private final ServerResponseInterface listener;

        private MyCallback(ServerResponseInterface listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            Log.d(MainActivity.TAG, "MyCallback.onResponse success " + (response != null ? response.isSuccessful() : "responseNULL"));
            if (response != null) {

                if (response.isSuccessful() && response.body() != null) {
                    listener.onResultsReceived(response.body());
                } else {
                    Log.e(MainActivity.TAG, "MyCallback.onResponse error code (" + response.code() + ") " + response.errorBody());
                    listener.onError("Unknown sever error " + response.code());
                }

            } else {
                Log.e(MainActivity.TAG, "MyCallback.onResponse null");
                listener.onError("Invalid data received");
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.e(MainActivity.TAG, "MyCallback.onFailure " + t);
            t.printStackTrace();
            listener.onError("Error requesting data to the server");
        }
    }
}
