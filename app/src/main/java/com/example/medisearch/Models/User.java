package com.example.medisearch.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    private String email;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("contactNo")
    private String contactNo;

    @SerializedName("password")
    private String password;

    @SerializedName("address")
    private String address;

    public User(String email, String fullname, String contactNo, String password, String address)
    {
        this.email      = email;
        this.fullname   = fullname;
        this.contactNo  = contactNo;
        this.password   = password;
        this.address    = address;
    }


    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}
