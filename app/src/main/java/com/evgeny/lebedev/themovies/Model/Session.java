package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Session {
    private boolean success;
    @SerializedName("session_id")
    private String sessionId;

    public boolean isSuccess() {
        return success;
    }

    public String getSessionId() {
        return sessionId;
    }
}
