package com.ksmart.schedule;


import com.ksmart.authen.model.Patient;
import com.ksmart.authen.repository.PatientRepository;
import com.ksmart.medicine.model.Medicine;
import com.ksmart.notification.payload.PushNotificationRequest;
import com.ksmart.notification.service.PushNotificationService;
import com.ksmart.schedule.model.Schedule;
import com.ksmart.schedule.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private List<Schedule> schedules = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PushNotificationService pushNotificationService;


    @Scheduled(fixedRate = 180 * 1000)
    public void checkSchedule() {
        List<Patient> patients = patientRepository.findAll();
        for(Patient p: patients){
            if(p.getDeviceToken()!=null && p.getDeviceToken()!=""){
                System.out.println(p.getDeviceToken());
                PushNotificationRequest request = new PushNotificationRequest();
                request.setToken(p.getDeviceToken());
                request.setTopic("Test");
                request.setTitle("Test");
                request.setMessage(dateFormat.format(new Date()));
                pushNotificationService.sendPushNotificationToToken(request);
            }
        }
        /*schedules = scheduleRepository.findByDayAndTimeBetween(DayOfWeek.from(LocalDate.now()), LocalTime.now().minusMinutes(30),LocalTime.now().plusMinutes(30));
        if(schedules.size()>0){
            for(Schedule sc : schedules){
                PushNotificationRequest req = new PushNotificationRequest();
                req.setTitle("กินยา");
                String message = "";
                for(Medicine med : sc.getMedicines()){
                    message = message+med+", ";
                }
                req.setTopic("MedicineAlert");
                req.setToken(sc.getPatient().getDeviceToken());
                pushNotificationService.sendPushNotificationToToken(req);
                log.info("sent notification to "+sc.getPatient().getUsername()+" The time is now {}", dateFormat.format(new Date()));
                sc.setLastNotice(new Date());
                scheduleRepository.save(sc);
            }

        }else{
            log.info("There is  no schedule");
        }*/

    }



}
