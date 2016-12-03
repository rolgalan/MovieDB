package io.rolgalan.moviedb;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.rolgalan.moviedb.data.DataInterface;
import io.rolgalan.moviedb.data.DataProvider;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;

/**
 * A fragment representing a list of {@link Movie}.
 */
public class ListMovieFragment extends Fragment implements DataInterface<MovieList> {
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListMovieFragment() {
    }

    @SuppressWarnings("unused")
    public static ListMovieFragment newInstance() {
        ListMovieFragment fragment = new ListMovieFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MovieRecyclerViewAdapter(DataProvider.ITEMS));

        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataProvider.discoverMovies(this);
    }

    @Override
    public void onResultsReceived(MovieList list) {
        if (recyclerView != null) recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
