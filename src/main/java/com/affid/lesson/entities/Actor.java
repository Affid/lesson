package com.affid.lesson.entities;

import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Actor {
    private final String name;
    private int age;
    private final LocalDate birthDate;
    private final LocalDate deathDate;

    public Actor(String name, LocalDate birthDate) {
        this.name = name;
        this.age = (int) ChronoPeriod.between(birthDate, LocalDate.now()).get(ChronoUnit.YEARS);
        this.birthDate = birthDate;
        deathDate = null;
    }

    public Actor(String name, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.age = (int) ChronoPeriod.between(birthDate, deathDate).get(ChronoUnit.YEARS);
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void refreshAge() {
        this.age = (int) ChronoPeriod.between(birthDate,
                Objects.requireNonNullElseGet(deathDate, LocalDate::now)).get(ChronoUnit.YEARS);
    }

    @Override
    public String toString() {
        return name;
    }
}
