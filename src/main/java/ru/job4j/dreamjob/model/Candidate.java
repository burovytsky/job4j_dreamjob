package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate {
    private String name;
    private String address;
    private String position;
    private LocalDateTime birthday;

    public Candidate(String name, String address, String position, LocalDateTime birthday) {
        this.name = name;
        this.address = address;
        this.position = position;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) &&
                Objects.equals(address, candidate.address) &&
                Objects.equals(position, candidate.position) &&
                Objects.equals(birthday, candidate.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, position, birthday);
    }
}
