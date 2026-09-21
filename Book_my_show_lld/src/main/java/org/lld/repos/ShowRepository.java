package org.lld.repos;

import org.lld.models.Movie;
import org.lld.theatre.Show;

import java.util.HashMap;
import java.util.Map;

public class ShowRepository {
    Map<String, Show> showMap=new HashMap<>();

    public void save(Show show){
        showMap.put(show.getId(),show);

    }

    public Map<String, Show> getShowMap() {
        return showMap;
    }

    public Show get(String id){
        return  showMap.get(id);
    }
}
