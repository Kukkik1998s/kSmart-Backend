package com.ksmart.schedule.payload;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.model.User;
import com.ksmart.medicine.model.Medicine;
import com.ksmart.schedule.model.UnitOfTime;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ScheduleResponse {
    private String id;
    private Patient patient;
    private User creator;
    private List<Medicine> medicines;
    private Date startDate;
    private int frequency;
    private UnitOfTime unitOfTime;
    private DayOfWeek day;
    private LocalTime time;

    public ScheduleResponse(String id, Patient patient, User creator, List<Medicine> medicines, Date startDate, int frequency, UnitOfTime unitOfTime, DayOfWeek day, LocalTime time) {
        this.id = id;
        this.patient = patient;
        this.creator = creator;
        this.medicines = medicines;
        this.startDate = startDate;
        this.frequency = frequency;
        this.unitOfTime = unitOfTime;
        this.day = day;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public UnitOfTime getUnitOfTime() {
        return unitOfTime;
    }

    public void setUnitOfTime(UnitOfTime unitOfTime) {
        this.unitOfTime = unitOfTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
