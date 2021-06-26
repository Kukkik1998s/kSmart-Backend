package com.ksmart.authen.payload.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class PatientResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private String name;
    private String lastname;
    private Date birthdate;
    private String address;
    String phoneNo;
    private Double weight;
    private Double height;
    private Double gfr;
    private String gender;
    private int ckdPhase;

    public PatientResponse(String accessToken, String id, String username, String email, List<String> roles, String name, String lastname, Date birthdate,
                           String address, String phoneNo, Double weight, Double height, Double gfr, String gender, int ckdPhase) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNo = phoneNo;
        this.weight = weight;
        this.height = height;
        this.gfr = gfr;
        this.gender = gender;
        this.ckdPhase = ckdPhase;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getGfr() {
        return gfr;
    }

    public void setGfr(Double gfr) {
        this.gfr = gfr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCkdPhase() {
        return ckdPhase;
    }

    public void setCkdPhase(int ckdPhase) {
        this.ckdPhase = ckdPhase;
    }
}
