package org.lld.theatre;

import org.lld.enums.SeatStatus;
import org.lld.models.Movie;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Show {
    String id;

    Movie movie;
    LocalDateTime start;

    Theatre theatre;
    Screen screen;

    public Show(String id, Movie movie, LocalDateTime start,  Theatre theatre, Screen screen) {
        this.id = id;
        this.movie = movie;
        this.start = start;

        this.theatre = theatre;
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public   List<Seat> getSeats(){
        List<Seat>seats=new ArrayList<>();
         screen.getSeatMap().forEach((k,v)->{seats.add(v);});
        return seats;
    }
}
