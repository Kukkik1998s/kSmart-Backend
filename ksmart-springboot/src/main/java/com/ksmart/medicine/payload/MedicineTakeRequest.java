package com.ksmart.medicine.payload;

import com.ksmart.medicine.model.Medicine;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

public class MedicineTakeRequest {
    String username;
    String time;
    String before;
    Date date;
    List<Medicine> medicines;

    public MedicineTakeRequest(String username, String time, String before, Date date, List<Medicine> medicines) {
        this.username = username;
        this.time = time;
        this.before = before;
        this.date = date;
        this.medicines = medicines;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
