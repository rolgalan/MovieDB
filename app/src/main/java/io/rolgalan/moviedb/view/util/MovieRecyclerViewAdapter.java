package io.rolgalan.moviedb.view.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rolgalan.moviedb.R;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie}
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final MovieList list;

    public MovieRecyclerViewAdapter(MovieList items) {
        list = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setMovie(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView pictureImageView;
        @BindView(R.id.item_title)
        TextView titleTextView;
        @BindView(R.id.item_year)
        TextView yearTextView;
        @BindView(R.id.item_overview)
        TextView overviewTextView;
        private View wholeView;
        private Movie movie;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.wholeView = view;
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

        public void setMovie(Movie movie) {
            this.movie = movie;
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getReleaseYear());
            overviewTextView.setText(movie.getOverview());
            loadImage(wholeView.getContext(), pictureImageView, movie.getPosterUrl());
        }

        private void loadImage(Context context, ImageView image, String imgUrl) {
            if (imgUrl != null && !imgUrl.isEmpty()) {
                Glide.with(context).load(imgUrl)
                        .fitCenter()
                        //TODO Create placeholder asset and load here .placeholder(R.drawable.placeholder)
                        .crossFade()
                        .into(image);
            }
        }
    }
}
