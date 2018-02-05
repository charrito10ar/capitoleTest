package com.marcelo.privalia.moviesapp.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcelo.privalia.moviesapp.R;
import com.marcelo.privalia.moviesapp.domain.model.Movie;
import com.marcelo.privalia.moviesapp.presentation.ui.display.LoaderImage;

import java.util.ArrayList;
import java.util.List;

public class MostPopularMoviesAdapter extends RecyclerView.Adapter<MostPopularMoviesAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    public MostPopularMoviesAdapter(){
        this.movieList = new ArrayList<>();
    }

    @Override
    public MostPopularMoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.movie_list, parent, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(MostPopularMoviesAdapter.MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bindMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addAll(List<Movie> movieList){
        this.movieList.addAll(movieList);
    }

    public void clear(){
        this.movieList.clear();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private TextView movieYear;
        private TextView overView;
        private ImageView movieImage;

        private MovieViewHolder(View itemView) {
            super(itemView);
            this.movieTitle = itemView.findViewById(R.id.text_view_title);
            this.movieYear = itemView.findViewById(R.id.text_view_year);
            this.overView = itemView.findViewById(R.id.text_view_over_view);
            this.movieImage = itemView.findViewById(R.id.image_movie);
        }

        private void bindMovie(Movie movie) {
            movieTitle.setText(movie.getTitle());
            movieYear.setText(itemView.getContext().getString(R.string.movie_release, String.valueOf(movie.getReleaseYear())));
            overView.setText(movie.getOverview() != null ? movie.getOverview() : "Sin Overview");
            LoaderImage.getInstance(itemView.getContext()).loadCoverMovie(itemView.getContext(), movie.getPosterPath(), movieImage);
        }
    }
}
