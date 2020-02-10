package com.Lyngbjerg;

public class Player {
    // Fields:
    private String playerName;
    private int score;
    private int tempScore;

    // Constructor:
    public Player(String playerName) { this.playerName = playerName; }

    // toString:
    public String toString() { return playerName; }

    // Getters:
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public int getTempScore() {
        return tempScore;
    }

    // Setters:
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setTempScore(int tempScore) {
        this.tempScore = tempScore;
    }
}