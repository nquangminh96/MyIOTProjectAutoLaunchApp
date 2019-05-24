package com.example.myiotproject_autolaunchapp;


public class ThietBi {
    String name;
    int level;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ThietBi(String name, int level, int type) {
        this.name = name;
        this.level = level;
        this.type = type;
    }

    public ThietBi() {
    }

    public ThietBi(String name,int level) {

        this.name = name;

        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
