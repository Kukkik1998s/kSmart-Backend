package com.ksmart.authen.model;

import com.ksmart.schedule.model.Schedule;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection = "patients")
public class Patient extends User {

    @DBRef
    private User user;
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    @Size(max = 30)
    private String lastname;
    private Date birthdate;
    @NotBlank
    @Size(max = 100)
    private String address;
    @NotBlank
    @Size(max=10)
    String phoneNo;
    @NotBlank
    private Double weight;
    @NotBlank
    private Double height;
    private Double gfr;
    @NotBlank
    private String gender;
    private int ckdPhase;
    @DBRef
    private List<Schedule> medSchedule;

    private String deviceToken;

    public Patient(User user, String name, String lastname, String address, Date birthdate,
                   String phoneNo, Double weight, Double height, Double gfr, String gender, int ckdPhase) {
        //super(username, email, password);
        this.user = user;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.birthdate = birthdate;
        this.phoneNo = phoneNo;
        this.weight = weight;
        this.height = height;
        this.gfr = gfr;
        this.gender = gender;
        this.ckdPhase = ckdPhase;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public List<Schedule> getMedSchedule() {
        return medSchedule;
    }

    public void setMedSchedule(List<Schedule> medSchedule) {
        this.medSchedule = medSchedule;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
