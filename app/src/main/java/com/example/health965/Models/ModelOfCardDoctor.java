package com.example.health965.Models;

public class ModelOfCardDoctor {
    int IamgeOfDoctoer, ImageOfClinics;
    String NameOfDoctor, NameOfClinic, Address, TypeOfWork, Dec;

    public ModelOfCardDoctor(int iamgeOfDoctoer, int imageOfClinics, String nameOfDoctor, String nameOfClinic, String address, String typeOfWork, String dec) {
        IamgeOfDoctoer = iamgeOfDoctoer;
        ImageOfClinics = imageOfClinics;
        NameOfDoctor = nameOfDoctor;
        NameOfClinic = nameOfClinic;
        Address = address;
        TypeOfWork = typeOfWork;
        Dec = dec;
    }

    public int getIamgeOfDoctoer() {
        return IamgeOfDoctoer;
    }

    public void setIamgeOfDoctoer(int iamgeOfDoctoer) {
        IamgeOfDoctoer = iamgeOfDoctoer;
    }

    public int getImageOfClinics() {
        return ImageOfClinics;
    }

    public void setImageOfClinics(int imageOfClinics) {
        ImageOfClinics = imageOfClinics;
    }

    public String getNameOfDoctor() {
        return NameOfDoctor;
    }

    public void setNameOfDoctor(String nameOfDoctor) {
        NameOfDoctor = nameOfDoctor;
    }

    public String getNameOfClinic() {
        return NameOfClinic;
    }

    public void setNameOfClinic(String nameOfClinic) {
        NameOfClinic = nameOfClinic;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTypeOfWork() {
        return TypeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        TypeOfWork = typeOfWork;
    }

    public String getDec() {
        return Dec;
    }

    public void setDec(String dec) {
        Dec = dec;
    }
}