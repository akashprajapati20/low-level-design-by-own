package org.lld.theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theatre {
    String id;
    String name;
    Map<String,Screen> screenMap;

    public Theatre(String id, String name) {
        this.id = id;
        this.name = name;
        screenMap=new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Screen> getScreenMap() {
        return screenMap;
    }

    public void setScreenMap(Map<String, Screen> screenMap) {
        this.screenMap = screenMap;
    }

    public void addScreen(Screen screen){
        screenMap.put(screen.getId(), screen);
    }
}
