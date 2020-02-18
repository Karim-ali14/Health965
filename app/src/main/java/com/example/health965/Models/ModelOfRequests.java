package com.example.health965.Models;

public class ModelOfRequests {
    private String Name,Email,Phone,Doctor,Date,Time,Status;

    public ModelOfRequests(String name, String email, String phone, String doctor, String date, String time, String status) {
        Name = name;
        Email = email;
        Phone = phone;
        Doctor = doctor;
        Date = date;
        Time = time;
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
