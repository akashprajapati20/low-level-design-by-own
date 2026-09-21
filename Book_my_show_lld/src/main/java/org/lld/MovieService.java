package org.lld;

import org.lld.repos.MovieRepository;

public class MovieService {
    MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepo) {
        movieRepository=movieRepo;
    }
}
