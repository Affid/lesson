package com.affid.lesson.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Film {
    private final String name;
    private final String director;
    private final LocalDate date;
    private final long gross;
    private final int duration;
    private final ArrayList<Actor> cast;
    private final ArrayList<String> genres;
    private final float IMDb;

    public ArrayList<String> getGenres() {
        return new ArrayList<>(genres);
    }

    public Film(String name, String director, LocalDate date, long gross, int duration, ArrayList<String> genres, float imDb, Actor... cast) {
        this.name = name;
        this.director = director;
        this.date = date;
        this.gross = gross;
        this.duration = duration;
        this.genres = genres;
        IMDb = imDb;
        this.cast = new ArrayList<>();
        this.cast.addAll(Arrays.asList(cast));
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getGross() {
        return gross;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Actor> getCast() {
        return new ArrayList<>(cast);
    }

    public float getIMDb() {
        return IMDb;
    }

    @Override
    public String toString() {
        return "Фильм:" +
                "\n\tНазвание: '" + name + '\'' +
                "\n\tРежиссер: " + director +
                "\n\tДата выхода: " + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) +
                "\n\tСборы: " + "$" + gross +
                "\n\tДлительность: " + duration + " мин." +
                "\n\tАктерский состав: " + cast.toString().substring(1, cast.toString().length() - 1) +
                "\n\tЖанры: " + genres.toString().substring(1, genres.toString().length() - 1) +
                "\n\tIMDb: " + IMDb + "\n";
    }
}
