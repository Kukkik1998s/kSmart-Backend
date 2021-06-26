package com.ksmart.schedule.model;

import com.ksmart.authen.model.ERole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "unit_of_time")
public class UnitOfTime {
    @Id
    private String id;
    private EUnitOfTime name;

    public UnitOfTime(EUnitOfTime name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EUnitOfTime getName() {
        return name;
    }

    public void setName(EUnitOfTime name) {
        this.name = name;
    }

}
