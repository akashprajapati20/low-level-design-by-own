package org.lld.repos;

import org.lld.theatre.Show;
import org.lld.theatre.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    Map<String, Theatre> theatreMap=new HashMap<>();

    public TheatreRepository() {
    }

    public void save(Theatre theatre){
        theatreMap.put(theatre.getId(),theatre);

    }

    public Theatre get(String id){
        return  theatreMap.get(id);
    }
}
