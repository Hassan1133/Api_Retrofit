package com.example.retrofitpractice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResponse {
    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }
}
