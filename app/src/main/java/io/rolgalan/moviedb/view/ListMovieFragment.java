package io.rolgalan.moviedb.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;

import butterknife.ButterKnife;
import io.rolgalan.moviedb.MainActivity;
import io.rolgalan.moviedb.R;
import io.rolgalan.moviedb.data.DataInterface;
import io.rolgalan.moviedb.data.DataProvider;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;
import io.rolgalan.moviedb.server.ApiManager;
import io.rolgalan.moviedb.view.util.MovieRecyclerViewAdapter;

/**
 * A fragment representing a list of {@link Movie}.
 */
public class ListMovieFragment extends Fragment implements DataInterface<MovieList> {
    private RecyclerView recyclerView;
    private FloatingSearchView searchView;

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
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new MovieRecyclerViewAdapter(DataProvider.ITEMS));
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        DataProvider.discoverMovies(0, this);
    }

    private void setupSearchView() {
        searchView = ButterKnife.findById(getActivity(), R.id.floating_search_view);
        searchView.showProgress();
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    ApiManager.getInstance().cancelCall();
                    searchView.hideProgress();
                } else {
                    searchView.showProgress();
                    DataProvider.searchMovies(newQuery, 0, ListMovieFragment.this);
                    Log.i(MainActivity.TAG, newQuery);
                }
            }
        });
    }

    @Override
    public void onResultsReceived(MovieList list) {
        searchView.hideProgress();
        if (recyclerView != null) recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        searchView.hideProgress();
        Toast.makeText(getContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
