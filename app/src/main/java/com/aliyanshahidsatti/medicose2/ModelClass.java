package com.aliyanshahidsatti.medicose2;

import android.graphics.Bitmap;

public class ModelClass {
    private String userName,email,phone,password;
    private Bitmap profileimage;

    public ModelClass(String userName, String email, String phone, String password, Bitmap profileimage) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profileimage = profileimage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(Bitmap profileimage) {
        this.profileimage = profileimage;
    }
}
