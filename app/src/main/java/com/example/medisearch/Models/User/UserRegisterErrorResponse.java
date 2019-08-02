package com.example.medisearch.Models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class UserRegisterErrorResponse {

    @SerializedName("email")
    @Expose
    private List<String> email = null;

    @SerializedName("contact_no")
    @Expose
    private List<String> contactNo = null;


    @SerializedName("fullname")
    @Expose
    private List<String> fullName = null;

    @SerializedName("password")
    @Expose
    private List<String> password = null;

    @SerializedName("address")
    @Expose
    private List<String> address = null;



    public List<String> getEmail() {
        return email;
    }

    public List<String> getContactNo() {
        return contactNo;
    }

    public List<String> getFullName() {
        return fullName;
    }

    public List<String> getPassword() {
        return password;
    }

    public List<String> getAddress() {
        return address;
    }
}


