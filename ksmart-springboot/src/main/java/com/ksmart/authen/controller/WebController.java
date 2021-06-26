package com.ksmart.authen.controller;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.model.Role;
import com.ksmart.authen.model.User;
import com.ksmart.authen.repository.PatientRepository;
import com.ksmart.authen.repository.UserRepository;
import com.ksmart.meal.model.Meal;
import com.ksmart.meal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.ksmart.authen.model.ERole.ROLE_DOCTOR;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/get")
public class WebController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MealRepository mealRepository;

    @GetMapping("/patients")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }

    @GetMapping("/this/patient")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public Patient getThisPatient(@RequestParam String username){
        User user = userRepository.findByUsername(username).get();
        return patientRepository.findByUser(user).get();
    }

    @GetMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getDoctors(){
        List<User> users = userRepository.findAll();
        List<User> doctors = new ArrayList<User>();
        //Role _role = roleRepository.findByName(ROLE_DOCTOR).get();
        for(User u: users){
            for(Role r : u.getRoles()){
                if(r.getName()== ROLE_DOCTOR){
                    doctors.add(u);
                }
            }
        }
        return doctors;
    }

    @GetMapping("/patient/meal")
    public List<Meal> patientMeal(@RequestParam String username){
        return mealRepository.findByUsername(username);
    }

}