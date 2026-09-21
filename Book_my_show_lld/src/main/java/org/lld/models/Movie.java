package org.lld.models;

import java.sql.Time;

public class Movie {
    String id;
    String title;
    int durationMin;

    public Movie(String id, String title, int durationMin) {
        this.id = id;
        this.title = title;
        this.durationMin = durationMin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }
}
