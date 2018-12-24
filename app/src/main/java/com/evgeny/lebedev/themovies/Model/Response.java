package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("status_code")
    private String statusCode;
    @SerializedName("status_message")
    private String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
