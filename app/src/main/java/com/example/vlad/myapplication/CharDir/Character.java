package com.example.vlad.myapplication.CharDir;

public class Character extends CharInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        this.clasa = clasa;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }

    private String name;
    private String realm;
    private String clasa;
    private int race;
    private int faction;

    public Character(){}


    public Character(String name, String realm, String clasa, int race, int faction) {
        this.name = name;
        this.realm = realm;
        this.clasa = clasa;
        this.race = race;
        this.faction = faction;
    }
}
