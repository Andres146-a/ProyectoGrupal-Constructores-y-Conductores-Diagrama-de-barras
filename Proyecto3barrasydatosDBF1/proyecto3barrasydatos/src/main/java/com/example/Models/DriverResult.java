package com.example.Models;

 
public class DriverResult {
    private String name;
    private int wins;
    private float totalPoints;
    private int seasonRank;

    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public float getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(float totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getSeasonRank() {
        return seasonRank;
    }

    public void setSeasonRank(int seasonRank) {
        this.seasonRank = seasonRank;
    }
}
