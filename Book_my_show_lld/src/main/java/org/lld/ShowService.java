package org.lld;

import org.lld.models.Movie;
import org.lld.repos.MovieRepository;
import org.lld.repos.ShowRepository;
import org.lld.repos.TheatreRepository;
import org.lld.theatre.Show;
import org.lld.theatre.Theatre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShowService {
    ShowRepository showRepository;
    MovieRepository movieRepository;
    TheatreRepository theatreRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public Show createShow(String showId, Movie movie, String theatreId, String screenId, LocalDateTime start){
        Theatre theatre=theatreRepository.get(theatreId);
        Show show=new Show(showId,movie,start,theatre,theatre.getScreenMap().get(screenId));
        showRepository.save(show);
        return show;
    }

    public List<Show> getShowsByMovieTitle(String title){
        Map<String, Show> map= showRepository.getShowMap();
        List<Show>shows=new ArrayList<>();

        map.forEach((key, value) -> {
           if(value.getMovie().getTitle().equals(title)){
               shows.add(value);
           }
        });

        return shows;
    }
}
