package com.affid.lesson;

import com.affid.lesson.entities.Actor;
import com.affid.lesson.entities.Film;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LocalCinemaClient implements CinemaClient {
    private final ArrayList<Film> films = new ArrayList<>();
    private final HashMap<String, Actor> actors = new HashMap<>();

    public LocalCinemaClient() {
        loadActors();
        loadFilms();
    }

    private void loadActors() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/actors.csv"))) {
            List<String[]> file = reader.readAll();
            for (int i = 1; i < file.size(); i++) {
                String name = file.get(i)[0];
                String[] date = file.get(i)[1].split("\\.");
                LocalDate birthDate = LocalDate.of(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                Actor actor;
                if (!file.get(i)[2].equals("-")) {
                    date = file.get(i)[2].split("\\.");
                    LocalDate deathDate = LocalDate.of(Integer.parseInt(date[2]),
                            Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                    actor = new Actor(name, birthDate, deathDate);
                } else
                    actor = new Actor(name, birthDate);
                this.actors.put(name, actor);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void loadFilms() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/films.csv"))) {
            List<String[]> file = reader.readAll();
            for (int i = 1; i < file.size(); i++) {
                String name = file.get(i)[0];
                String director = file.get(i)[1];
                String[] date = file.get(i)[2].split("\\.");
                LocalDate startDate = LocalDate.of(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                long gross = Long.parseLong(file.get(i)[3]);
                int duration = Integer.parseInt(file.get(i)[4]);
                String[] actorNames = file.get(i)[5].split(", ");
                Actor[] actors = new Actor[actorNames.length];
                for (int j = 0; j < actors.length; j++)
                    actors[j] = this.actors.get(actorNames[j]);
                float rate = Float.parseFloat(file.get(i)[6]);
                String[] genres = file.get(i)[7].split(", ");
                Film film = new Film(name, director, startDate, gross, duration,
                        new ArrayList<>(Arrays.asList(genres)), rate, actors);
                this.films.add(film);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Film> getFilms() {
        return new ArrayList<>(films);
    }

    @Override
    public ArrayList<Film> getFilms(float rate) {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : this.films) {
            if (film.getIMDb() == rate)
                films.add(film);
        }
        return films;
    }

    @Override
    public ArrayList<Film> getFilms(List<String> genres) {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : this.films) {
            boolean toAdd = true;
            for (String genre : genres)
                if (!film.getGenres().contains(genre)) {
                    toAdd = false;
                }
            if (toAdd)
                films.add(film);
        }
        return films;
    }

    @Override
    public ArrayList<Film> getFilms(int year) {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : this.films) {
            if (film.getDate().getYear() == year){
                films.add(film);
            }
        }
        return films;
    }

    @Override
    public ArrayList<Film> getFilms(String director) {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : this.films) {
            if (film.getDirector().equals(director)){
                films.add(film);
            }
        }
        return films;
    }

    @Override
    public Film getFilm(String name) {
        for (Film film : this.films) {
            if (film.getName().equals(name)){
                return film;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Film> getFilmsByActor(String name) {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : this.films) {
            if (film.getCast().contains(actors.get(name))){
                films.add(film);
            }
        }
        return films;
    }

    @Override
    public ArrayList<Actor> getActors() {
        return new ArrayList<>(actors.values());
    }


}
