package com.example.wotstat.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private int totalBattles;
    private float winRate;
    private float averageDamage;
    private float averageExp;
    private String clanName;
//    @ManyToOne
//    @JoinColumn(name = "clan_id")
//    private Clan clan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTotalBattles() {
        return totalBattles;
    }

    public void setTotalBattles(int totalBattles) {
        this.totalBattles = totalBattles;
    }

    public float getWinRate() {
        return winRate;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public float getAverageDamage() {
        return averageDamage;
    }

    public void setAverageDamage(float averageDamage) {
        this.averageDamage = averageDamage;
    }

    public float getAverageExp() {
        return averageExp;
    }

    public void setAverageExp(float averageExp) {
        this.averageExp = averageExp;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

//    public Clan getClan() {
//        return clan;
//    }
//
//    public void setClan(Clan clan) {
//        this.clan = clan;
//    }
}
