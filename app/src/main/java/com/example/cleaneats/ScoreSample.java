package com.example.cleaneats;

public class ScoreSample {
    private String name;
    private String address;
    private String date;
    private int score;

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
