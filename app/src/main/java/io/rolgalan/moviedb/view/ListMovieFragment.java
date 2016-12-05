package io.rolgalan.moviedb.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class ListMovieFragment extends Fragment implements DataInterface<MovieList>, MovieRecyclerViewAdapter.LoadMoreListener {
    private RecyclerView recyclerView;
    private FloatingSearchView searchView;
    private boolean isLoading = false;
    private String currentQuery = null;
    private Button loadMoreButton;

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
            setupRecyclerView((RecyclerView) view);
        }
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        DataProvider.discoverMovies(1, this);
    }

    private void setupRecyclerView(RecyclerView view) {
        recyclerView = view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MovieRecyclerViewAdapter(DataProvider.ITEMS, this));

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int total = linearLayoutManager.getItemCount();
                int lastVisibleItemCount = linearLayoutManager.findLastVisibleItemPosition();
                // Log.i(MainActivity.TAG, "Total " + total + " - lastVisibleItemCount: " + lastVisibleItemCount);

                if (total > 0) {
                    //Load new page when 10 elements left for end
                    if ((total - 10) == lastVisibleItemCount) {
                        loadMore();
                    }
                }
            }
        });
    }

    public void loadMore() {
        Log.i(MainActivity.TAG, "loadMoreButton");
        if (!isLoading) {
            if (loadMoreButton != null) loadMoreButton.setEnabled(false);
            searchView.showProgress();
            isLoading = true;
            int nextPage = DataProvider.ITEMS.getPage() + 1;
            if (currentQuery != null) {
                DataProvider.searchMovies(currentQuery, nextPage, ListMovieFragment.this);
            } else {
                DataProvider.discoverMovies(nextPage, this);
            }
        }
    }

    @Override
    public void setLoadMoreButon(Button button) {
        this.loadMoreButton = button;
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
                    DataProvider.searchMovies(newQuery, 1, ListMovieFragment.this);
                    currentQuery = newQuery;
                    Log.i(MainActivity.TAG, newQuery);
                }
            }
        });
    }

    private void onRequestEnded() {
        searchView.hideProgress();
        isLoading = false;
        if (loadMoreButton != null) loadMoreButton.setEnabled(true);
    }

    @Override
    public void onResultsReceived(MovieList list) {
        onRequestEnded();
        if (recyclerView != null) recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        onRequestEnded();
        Toast.makeText(getContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
