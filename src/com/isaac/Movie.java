package com.isaac;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int movieId;
    private float rating;
    private ArrayList<Movie> similarMovies = new ArrayList<>();

    public Movie(int movieId, float rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public int getId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public void addSimilarMovie(Movie movie) {
        similarMovies.add(movie);
    }

    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }
}
