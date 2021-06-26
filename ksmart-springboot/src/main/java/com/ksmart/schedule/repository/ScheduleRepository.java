package com.ksmart.schedule.repository;

import com.ksmart.authen.model.Patient;
import com.ksmart.schedule.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    @Override
    Optional<Schedule> findById(String s);

    List<Schedule> findByPatient(Patient patient);
    List<Schedule> findByDay(DayOfWeek day);
    List<Schedule> findByTime(LocalTime time);
    List<Schedule> findByDayAndTimeBetween(DayOfWeek day, LocalTime start, LocalTime end);
}
