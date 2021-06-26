package com.ksmart.authen.controller;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ksmart.authen.mail.EmailSenderService;
import com.ksmart.authen.model.*;
import com.ksmart.authen.payload.request.AddUserRequest;
import com.ksmart.authen.payload.request.EditPatientInfoRequest;
import com.ksmart.authen.payload.response.PatientInfo;
import com.ksmart.authen.payload.response.PatientResponse;
import com.ksmart.authen.repository.ConfirmationTokenRepository;
import com.ksmart.authen.repository.PatientRepository;
import com.ksmart.authen.repository.RoleRepository;
import com.ksmart.authen.repository.UserRepository;
import com.ksmart.authen.security.JwtUtils;
import com.ksmart.authen.service.UserDetailsImpl;
import com.ksmart.schedule.model.Schedule;
import com.ksmart.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.ksmart.authen.payload.request.LoginRequest;
import com.ksmart.authen.payload.request.SignupRequest;
import com.ksmart.authen.payload.response.JwtResponse;
import com.ksmart.authen.payload.response.MessageResponse;

import static com.ksmart.authen.model.ERole.ROLE_PATIENT;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ScheduleRepository scheduleRepository;

    String url = "http://localhost:8080";
    //String url = "https://ksmart-springboot-mongodb.herokuapp.com";


    @GetMapping("/iscurrent/{email}")
    public String isCurrentUser(@PathVariable String email){
        if(userRepository.existsByEmail(email)) return "exist";
        return "not exist";

    }

    @PutMapping
    public String patientSignout(@RequestParam String id){
        Patient p = patientRepository.findById(id).get();
        p.setDeviceToken("");
        patientRepository.save(p);
        return "signed out device token will be"+p.getDeviceToken();
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        //System.out.println(jwt);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        //Role prole = roleRepository.findByName(ROLE_PATIENT).get();
        boolean isPatient = roles.contains(ROLE_PATIENT.toString());
        if(isPatient==true) {

            User user = userRepository.findByUsername(userDetails.getUsername()).get();
            //System.out.println(user.getUsername());
            Patient patient = patientRepository.findByUser(user).get();
            //  System.out.println(patient.getName());
            //String name, String lastname, Date birthdate, String address, String phoneNo, Double weight, Double height,
            // Double gfr, String gender, int ckdPhase
            PatientResponse patientRes = new PatientResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles, patient.getName(), patient.getLastname(), patient.getBirthdate(),
                    patient.getAddress(), patient.getPhoneNo(), patient.getWeight(), patient.getHeight(), patient.getGfr(),
                    patient.getGender(), patient.getCkdPhase());
            List<Schedule> medScheedule = scheduleRepository.findByPatient(patient);
            patient.setMedSchedule(medScheedule);

            if(user.isEnable()!=true){
                ConfirmationToken confirmationToken = new ConfirmationToken(user);

                confirmationTokenRepository.save(confirmationToken);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setFrom("webproj2562@gmail.com");
                mailMessage.setText("To confirm your account, please click here : "
                        +url+"/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

                emailSenderService.sendEmail(mailMessage);
                //user.setEnable(true);
                userRepository.save(user);
            }

            return ResponseEntity.ok(patientRes);
        }else{
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest addUserRequest){
        if (userRepository.existsByUsername(addUserRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(addUserRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(addUserRequest.getUsername(),
                addUserRequest.getEmail(),
                encoder.encode(addUserRequest.getPassword()));

        Set<String> strRoles = addUserRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "doctor":
                    Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(doctorRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(ROLE_PATIENT)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        user.setEnable(true);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/patient/edit/{id}")
    public ResponseEntity<?> updatePatientInfo(@PathVariable("id") String id,@RequestBody EditPatientInfoRequest request){
        User user = userRepository.findById(id).get();
        Patient patient = patientRepository.findByUser(user).get();
        patient.setName(request.getName());
        patient.setLastname(request.getLastname());
        patient.setBirthdate(request.getBirthdate());
        patient.setCkdPhase(request.getCkdPhase());
        patient.setAddress(request.getAddress());
        patient.setGender(request.getGender());
        patient.setGfr(request.getGfr());
        patient.setHeight(request.getHeight());
        patient.setWeight(request.getWeight());
        patient.setPhoneNo(request.getPhoneNo());

        patientRepository.save(patient);

       return ResponseEntity.ok(new PatientInfo(patient.getName(),patient.getLastname(),patient.getBirthdate(),
                patient.getAddress(),patient.getPhoneNo(),patient.getWeight(),patient.getHeight(),patient.getGfr(),
                patient.getGender(),patient.getCkdPhase()));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws ParseException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ROLE_PATIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "doctor":
                        Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ROLE_PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    /*Patient(User user, String name, String lastname, Date birthdate, String address, String phoneNo,
                   Double weight, Double height, Double gfr, String gender, int ckdPhase) */
            Patient patient = new Patient(user, signUpRequest.getName(), signUpRequest.getLastname(),
                    signUpRequest.getAddress(), signUpRequest.getBirthdate(), signUpRequest.getPhoneNo(), signUpRequest.getWeight(),
                    signUpRequest.getHeight(), signUpRequest.getGfr(), signUpRequest.getGender(), signUpRequest.getCkdPhase());
       /* Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(signUpRequest.getBirthdate());
        patient.setBirthdate(birthdate);*/
            patientRepository.save(patient);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("webproj2562@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +url+"/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?>  confirmUserAccount(@RequestParam String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if(token != null)
        {
            User user = userRepository.findByEmail(confirmationToken.getUser().getEmail()).get();
            user.setEnable(true);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Account verified"));
        }
        else
        {
            return ResponseEntity.ok(new MessageResponse("Account is failed to verify"));
        }
    }

    @GetMapping("/finduser")
    public User findUser(@RequestParam String username){
        User user = userRepository.findByUsername(username).get();
         return user;
    }

    @GetMapping("/findpatient")
    public ResponseEntity<?> findPatient(@RequestParam String username){
        User user = userRepository.findByUsername(username).get();
        Patient patient = patientRepository.findByUser(user).get();
        return  ResponseEntity.ok(new PatientInfo(patient.getName(),patient.getLastname(),patient.getBirthdate(),
                patient.getAddress(),patient.getPhoneNo(),patient.getWeight(),patient.getHeight(),patient.getGfr(),
                patient.getGender(),patient.getCkdPhase()));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String token, @RequestParam String newPassword){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if(token != null) {
            User user = userRepository.findByEmail(confirmationToken.getUser().getEmail()).get();
            user.setEnable(true);
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Password already changed!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("The token is invalid or broken!"));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        User user = userRepository.findByEmail(email).get();
        if (user != null) {
            // create token
            ConfirmationToken conf = confirmationTokenRepository.findByUser(user);
            confirmationTokenRepository.delete(conf);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            // save it
             confirmationTokenRepository.save(confirmationToken);

            // create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Change Password Request!");
            mailMessage.setFrom("webproj2562@gmail.com");
            mailMessage.setText("To complete the password reset process, please copy this confirmation token and paste in your application: \n"
                    +confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new MessageResponse("Request to reset password received. Check your inbox for the reset link."));
        }else{
            return ResponseEntity.ok(new MessageResponse("This email does not exist!"));
        }
    }

    @GetMapping("/confirm-reset")
    public ResponseEntity<?> validateResetToken(@RequestParam String token)
    {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if(token != null) {
            User user = userRepository.findByEmail(confirmationToken.getUser().getEmail()).get();
            user.setEnable(true);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse(user.getEmail()));
        } else {
            return ResponseEntity.ok(new MessageResponse("The link is invalid or broken!"));
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String username){
        User user = userRepository.findByUsername(username).get();
        if(user.getEmail() != null) {
            // use email to find user
            User tokenUser = userRepository.findByEmail(user.getEmail()).get();
            tokenUser.setEnable(true);
            tokenUser.setPassword(encoder.encode(user.getPassword()));
            // System.out.println(tokenUser.getPassword());
            userRepository.save(tokenUser);
            return ResponseEntity.ok(new MessageResponse("Password successfully reset. You can now log in with the new credentials"));
        } else {
            return ResponseEntity.ok(new MessageResponse("The link is invalid or broken!"));
        }
    }

}