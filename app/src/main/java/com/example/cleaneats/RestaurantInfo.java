package com.example.cleaneats;

import java.util.List;

public class RestaurantInfo {
    private String name;
    private String address;
    private String date;
    private int score;
    private List<String> observations;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getObservations() { return observations; }

    public void setObservations(List<String> observations) { this.observations = observations; }

    @Override
    public String toString() {
        return "ScoreSample{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", score=" + score +
                '}';
    }
}
