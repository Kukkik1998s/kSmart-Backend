package com.ksmart.authen.payload.request;

import java.util.Date;

public class EditPatientInfoRequest {
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

    public EditPatientInfoRequest(String name, String lastname, Date birthdate, String address,
                                  String phoneNo, Double weight, Double height, Double gfr,
                                  String gender, int ckdPhase) {
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
