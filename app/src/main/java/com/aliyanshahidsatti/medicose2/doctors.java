package com.aliyanshahidsatti.medicose2;

public class doctors {

    String FullName, City, Profession, Day,Email,Password;

    public doctors() {
    }

    public doctors(String fullName, String city, String profession, String day, String email, String password) {
        FullName = fullName;
        City = city;
        Profession = profession;
        Day = day;
        Email = email;
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}