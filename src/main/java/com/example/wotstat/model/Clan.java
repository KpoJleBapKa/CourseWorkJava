package com.example.wotstat.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clanName;
    private int totalMembers;
    private int averageBattles;
    private float winRate;
    private float clanRating;
    private float averageExp;
    private float averageDamage;

    @OneToMany(mappedBy = "clan")
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getAverageBattles() {
        return averageBattles;
    }

    public void setAverageBattles(int averageBattles) {
        this.averageBattles = averageBattles;
    }

    public float getWinRate() {
        return winRate;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public float getClanRating() {
        return clanRating;
    }

    public void setClanRating(float clanRating) {
        this.clanRating = clanRating;
    }

    public float getAverageExp() {
        return averageExp;
    }

    public void setAverageExp(float averageExp) {
        this.averageExp = averageExp;
    }

    public float getAverageDamage() {
        return averageDamage;
    }

    public void setAverageDamage(float averageDamage) {
        this.averageDamage = averageDamage;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
