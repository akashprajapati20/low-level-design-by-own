package org.lld;

import org.lld.models.Movie;
import org.lld.repos.MovieRepository;

public class MovieService {
    MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepo) {
        movieRepository=movieRepo;
    }

    public Movie createMovie(String id, String title, int durationMin){
        Movie movie = new Movie(id, title, durationMin);
        movieRepository.save(movie);
        return movie;
    }
}
