package com.ksmart.schedule.controller;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.payload.response.MessageResponse;
import com.ksmart.authen.repository.PatientRepository;
import com.ksmart.schedule.model.Schedule;
import com.ksmart.schedule.payload.ScheduleRequest;
import com.ksmart.schedule.payload.ScheduleResponse;
import com.ksmart.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createMedicineSchedule(@RequestBody ScheduleRequest request){
        if(request!=null){
            Schedule schedule = new Schedule(request.getPatient(),request.getCreator(),request.getMedicines(),request.getStartDate(),request.getFrequency(),request.getUnitOfTime(),request.getDay(),request.getTime());
            schedule.setLatestUpdate(new Date());
            Patient patient = request.getPatient();
            List<Schedule> schedules = patient.getMedSchedule();
            schedules.add(schedule);
            patient.setMedSchedule(schedules);
            scheduleRepository.save(schedule);
            patientRepository.save(patient);
            return ResponseEntity.ok(new ScheduleResponse(schedule.getId(), schedule.getPatient(), schedule.getCreator(),schedule.getMedicines(),
                    schedule.getStartDate(),schedule.getFrequency(),schedule.getUnitOfTime(),schedule.getDay(),schedule.getTime()));
        }else{
            return ResponseEntity.ok(new MessageResponse("Cannot create medicine schedule!"));
        }
    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateMedicineSchedule(@RequestBody ScheduleRequest request){
        if(request!=null){
            Schedule schedule = new Schedule(request.getPatient(),request.getCreator(),request.getMedicines(),request.getStartDate(),request.getFrequency(),request.getUnitOfTime(),
                    request.getDay(),request.getTime());
            schedule.setLatestUpdate(new Date());
            scheduleRepository.save(schedule);
            return ResponseEntity.ok(new ScheduleResponse(schedule.getId(), schedule.getPatient(), schedule.getCreator(),schedule.getMedicines(),
                    schedule.getStartDate(),schedule.getFrequency(),schedule.getUnitOfTime(),schedule.getDay(),schedule.getTime()));
        }else{
            return ResponseEntity.ok(new MessageResponse("Cannot update medicine schedule!"));
        }
    }

    @GetMapping("/get")
    public Schedule getMedicineSchedule(@RequestParam String id)
    {
        return scheduleRepository.findById(id).get();
    }

    @GetMapping("/get/list-of")
    public List<Schedule> getMedicineScheduleOfPatient(@RequestParam String id){
        Patient patient = patientRepository.findById(id).get();
        return  scheduleRepository.findByPatient(patient);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMedicineSchedule(@RequestParam String id){
        Schedule schedule = scheduleRepository.findById(id).get();
        scheduleRepository.delete(schedule);
        if(scheduleRepository.findById(id)==null){
            return ResponseEntity.ok(new MessageResponse("This schedule is already deleted"));
        }else{
            return ResponseEntity.ok(new MessageResponse("Cannot delete this schedule"));
        }
    }

}
