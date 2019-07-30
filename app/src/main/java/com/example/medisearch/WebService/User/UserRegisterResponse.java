package com.example.medisearch.WebService.User;

import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {
    @SerializedName("registered")
    private boolean isRegistered;

    public boolean isRegistered() {
        return isRegistered;
    }

    //    @SerializedName("id")
//    private int id;
//
//    @SerializedName("email")
//    private String email;
//
//    @SerializedName("fullname")
//    private String fullname;
//
//    @SerializedName("contact_no")
//    private String contactNo;
//
//    @SerializedName("password")
//    private String password;
//
//    @SerializedName("address")
//    private String address;

//    public int getId() { return id; }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getFullname() {
//        return fullname;
//    }
//
//    public String getContactNo() {
//        return contactNo;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getAddress() {
//        return address;
//    }
}
