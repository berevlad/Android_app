package com.example.vlad.myapplication.RealmDir;



public class Realm {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;

    private String type;

    public Realm(){

    }

    public Realm(String name, String type) {
        this.name = name;
        this.type = type;
    }





}