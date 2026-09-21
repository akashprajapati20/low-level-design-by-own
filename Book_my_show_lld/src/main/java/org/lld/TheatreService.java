package org.lld;

import org.lld.repos.TheatreRepository;
import org.lld.theatre.Screen;
import org.lld.theatre.Seat;
import org.lld.theatre.Theatre;

public class TheatreService {
    TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public  void addTheatre(Theatre theatre){
        theatreRepository.save(theatre);
    }

    public Theatre createTheatre(String id,String name){
        Theatre theatre=new Theatre(id,name);
      addTheatre(theatre);
        return theatre;
    }

   public void addScreen(String id,Screen screen){
    Theatre theatre= theatreRepository.get(id);
    theatre.addScreen(screen);

    }

   public void addSeat(String theatreId,String screenId, Seat seat){
       Theatre theatre= theatreRepository.get(theatreId);
      Screen screen= theatre.getScreenMap().get(screenId);
      screen.addSeat(seat);

    }

}
