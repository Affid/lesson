package com.affid.lesson;

import com.affid.lesson.entities.Actor;
import com.affid.lesson.entities.Film;

import java.util.ArrayList;
import java.util.List;

public interface CinemaClient {

    ArrayList<Film> getFilms(List<String> genres);

    ArrayList<Film> getFilms(int year);

    ArrayList<Film> getFilms(String director);

    Film getFilm(String name);

    ArrayList<Film> getFilms(float rate);

    ArrayList<Film> getFilms();

    ArrayList<Film> getFilmsByActor(String name);

    ArrayList<Actor> getActors();
}
