package com.example.medisearch.Models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("contact_no")
    @Expose
    private String contactNo;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("address")
    @Expose
    private String address;

    public int getId() { return id; }

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
