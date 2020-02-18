package com.example.health965.Models;

public class ModelsForCilinics {
    private int Image;
    private String Name,Address,TypeWork;

    public ModelsForCilinics(int image, String name, String address, String typeWork) {
        Image = image;
        Name = name;
        Address = address;
        TypeWork = typeWork;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTypeWork() {
        return TypeWork;
    }

    public void setTypeWork(String typeWork) {
        TypeWork = typeWork;
    }
}
