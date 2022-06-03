package org.github.dumijdev.restwithjdbc.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private final long serialVersion = 1L;

    private long id;
    private String name;
    private LocalDate birthDate;

    public Student() {
    }

    public Student(long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, name: '%s', birthDate: %s}", id, name, birthDate);
    }
}
