package com.example.health965.Models;

public class ModelOfCertificates {
    private String NameCertificates,Date;
    public ModelOfCertificates(String nameCertificates, String date) {
        NameCertificates = nameCertificates;
        Date = date;
    }

    public String getNameCertificates() {
        return NameCertificates;
    }

    public void setNameCertificates(String nameCertificates) {
        NameCertificates = nameCertificates;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
