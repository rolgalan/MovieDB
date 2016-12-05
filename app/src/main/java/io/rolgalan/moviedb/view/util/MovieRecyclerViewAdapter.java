package io.rolgalan.moviedb.view.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rolgalan.moviedb.R;
import io.rolgalan.moviedb.model.Movie;
import io.rolgalan.moviedb.model.MovieList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie}
 */
public class MovieRecyclerViewAdapter extends FooterRecyclerViewAdapter<Movie> {
    private final LoadMoreListener listener;

    public MovieRecyclerViewAdapter(MovieList items, LoadMoreListener listener) {
        super(items);
        this.listener = listener;
    }

    @Override
    protected MovieViewHolder getItemView(LayoutInflater inflater, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    protected FooterViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position, Movie data) {
        ((MovieViewHolder) holder).setMovie(data);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
        listener.setLoadMoreButon(((FooterViewHolder) holder).getButton());
    }

    public interface LoadMoreListener {
        void loadMore();

        void setLoadMoreButon(Button button);
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loadmore_button)
        Button button;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public Button getButton(){
            return button;
        }

        @OnClick(R.id.loadmore_button)
        public void onButtonClicked() {
            button.setEnabled(false);
            if (listener != null) listener.loadMore();
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
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

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.wholeView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + movie.getTitle() + "'";
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
                Glide.with(context)
                        .load(imgUrl)
                        .fitCenter()
                        .placeholder(R.drawable.placeholder)
                        .crossFade()
                        .into(image);
            }
        }
    }
}
