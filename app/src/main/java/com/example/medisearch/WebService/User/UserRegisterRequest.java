package com.example.medisearch.WebService.User;

import com.google.gson.annotations.SerializedName;

public class UserRegisterRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("contact_no")
    private String contactNo;

    @SerializedName("password")
    private String password;

    @SerializedName("address")
    private String address;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
