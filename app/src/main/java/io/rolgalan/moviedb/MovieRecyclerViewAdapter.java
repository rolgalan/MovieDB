package io.rolgalan.moviedb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie}
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final MovieList mValues;

    public MovieRecyclerViewAdapter(MovieList items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setMovie(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View wholeView;
        @BindView(R.id.item_image)
        ImageView pictureImageView;
        @BindView(R.id.item_title)
        TextView titleTextView;
        @BindView(R.id.item_year)
        TextView yearTextView;
        @BindView(R.id.item_overview)
        TextView overviewTextView;
        private Movie movie;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.wholeView = view;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getReleaseYear());
            overviewTextView.setText(movie.getOverview());
            //TODO load movie.getPosterUrl() into pictureImageView using Glide
        }

        @Override
        public String toString() {
            return super.toString() + " '" + movie.getTitle() + "'";
        }

        public View getWholeView() {
            return wholeView;
        }

        public Movie getMovie() {
            return movie;
        }
    }
}
