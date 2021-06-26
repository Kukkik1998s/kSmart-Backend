package com.ksmart.notification.payload;

import com.ksmart.authen.model.Patient;

public class TokenRequest {
    private String token;
    private String patient;

    public TokenRequest(String token, String patient) {
        this.token = token;
        this.patient = patient;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
