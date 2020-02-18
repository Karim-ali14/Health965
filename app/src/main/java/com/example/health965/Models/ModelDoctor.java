package com.example.health965.Models;

public class ModelDoctor {
    private int Image;
    private String Name, Dec;
    private boolean isSelected;

    public ModelDoctor(int image, String name, String dec, boolean isSelected) {
        Image = image;
        Name = name;
        Dec = dec;
        this.isSelected = isSelected;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}