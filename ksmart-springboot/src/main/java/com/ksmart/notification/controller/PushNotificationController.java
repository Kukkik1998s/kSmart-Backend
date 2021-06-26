package com.ksmart.notification.controller;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.payload.response.MessageResponse;
import com.ksmart.authen.repository.PatientRepository;
import com.ksmart.notification.payload.PushNotificationRequest;
import com.ksmart.notification.payload.PushNotificationResponse;
import com.ksmart.notification.payload.TokenRequest;
import com.ksmart.notification.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    PatientRepository patientRepository;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

   /* @PostMapping("/notification/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity sendSampleNotification() {
        try {
            pushNotificationService.sendSamplePushNotification();
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping("/notification/device-token")
    public ResponseEntity getDeviceToken(@RequestParam String token, @RequestParam String id){
        System.out.println(id);
        Patient patient = patientRepository.findById(id).get();
        patient.setDeviceToken(token);
        patientRepository.save(patient);
        return ResponseEntity.ok(new MessageResponse("Got token"));
    }
}
