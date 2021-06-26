package com.ksmart.medicine.controller;


import com.ksmart.authen.payload.response.MessageResponse;
import com.ksmart.meal.model.Ingredient;
import com.ksmart.meal.service.ImageService;
import com.ksmart.medicine.model.Medicine;
import com.ksmart.medicine.model.MedicineTake;
import com.ksmart.medicine.payload.MedicineRequest;
import com.ksmart.medicine.payload.MedicineTakeRequest;
import com.ksmart.medicine.payload.MedicineTakeResponse;
import com.ksmart.medicine.repository.MedicineRepository;
import com.ksmart.medicine.repository.MedicineTakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/med")
public class MedicineController {
    /*@Autowired
    MedService medService;*/
    @Autowired
    MedicineRepository medRepo;
    @Autowired
    MedicineTakeRepository medicineTakeRepository;
    @Autowired
    ImageService service;

    @PostMapping("/add")
    public ResponseEntity<?> addMed(@Valid @RequestBody MedicineRequest medicineRequest){

        Medicine med = new Medicine(medicineRequest.getName(),medicineRequest.getQuantity(),medicineRequest.getUnit());
        med.setLatestUpdate(new Date());
        medRepo.save(med);
        String ref = med.getId();
        service.getRef(ref);

        return ResponseEntity.ok(new MessageResponse(med.getId()));

    }

    /*@PostMapping("/add")
    public ResponseEntity<?> addImage(@Valid @RequestParam("name") String name,
                                      @RequestParam("quantity") Double quantity,
                                      @RequestParam("unit") String unit,
                                      @RequestParam("image") MultipartFile image) throws IOException {

        Medicine med = new Medicine(name,quantity,unit);
        medService.addMedImage(med,image);

        return ResponseEntity.ok(new MessageResponse("Add medicine successfully!"));
    }*/

    @PostMapping("/take")
    public ResponseEntity<?> addMedTake(@Valid @RequestBody MedicineTakeRequest request){
        MedicineTake medicineTake = new MedicineTake(request.getUsername(),request.getTime(),request.getBefore(),
                request.getDate(),request.getMedicines());
        medicineTakeRepository.save(medicineTake);

        return ResponseEntity.ok(new MedicineTakeResponse(medicineTake.getId(),medicineTake.getUsername(),medicineTake.getTime(),
                medicineTake.getBefore(),medicineTake.getDate(),medicineTake.getMedicines()));
    }

    @GetMapping("/get/record/{username}")
    public List<MedicineTake> getMedTakeRecord(@PathVariable("username") String username){
        return  medicineTakeRepository.findByUsername(username);
    }

    @GetMapping("get/record/{username}/range")
    public List<MedicineTake> getMedTakeRecordInRange(@PathVariable("username") String username, @RequestParam String start, @RequestParam String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date date = sdf.parse(start);
        Date _date = sdf.parse(end);
        return medicineTakeRepository.findByUsernameAndDateBetween(username,date,new Date(_date.getTime() + MILLIS_IN_A_DAY));
    }

    @GetMapping("/get/all/record")
    public  List<MedicineTake> getAllRecord(){
        return medicineTakeRepository.findAll();
    }

    @GetMapping("/get/{user}")
    public List<MedicineTake> getMedTakeByUser(@PathVariable("user") String username){
        return medicineTakeRepository.findByUsername(username);
    }

    @GetMapping("/get")
    public List<Medicine> medicines(){
        List<Medicine> medicines = medRepo.findAll();
        for(Medicine med: medicines){
            Date update = med.getLatestUpdate();
            if(update==null){
                update = new Date();
                med.setLatestUpdate(update);
                medRepo.save(med);
            }
        }
        return  medRepo.findAll();
    }

}
