package com.example.retrofitpractice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("father_name")
    @Expose
    private String father_name;

    @SerializedName("department")
    @Expose
    private String department;

    @SerializedName("email")
    @Expose
    private String email;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }
}
