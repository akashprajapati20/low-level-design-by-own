package org.lld.repos;

import org.lld.models.Booking;
import org.lld.models.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    Map<String, Movie> movieMap=new HashMap<>();

    public void save(Movie movie){
        movieMap.put(movie.getId(),movie);

    }

    public Movie get(String id){
        return  movieMap.get(id);
    }
}
