package com.example.health965.Models;

public class Model {
    private String Name, Dec;
    private int Image;

    public Model(String name, String dec, int image) {
        Name = name;
        Dec = dec;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDec() {
        return Dec;
    }

    public void setDec(String dec) {
        Dec = dec;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}