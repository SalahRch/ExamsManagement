package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.Admin;
import com.lorem.ExamsManagement.model.Staff;
import com.lorem.ExamsManagement.security.authentication.AuthenticationRequest;
import com.lorem.ExamsManagement.security.authentication.AuthenticationResponse;
import com.lorem.ExamsManagement.security.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {


    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/RegisterAdmin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Admin admin){
        return ResponseEntity.ok(authenticationService.registerAdmin(admin));
    }

    @PostMapping("/RegisterStaff")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Staff staff, @RequestParam("type") String type){
        System.out.println(staff);
        return ResponseEntity.ok(authenticationService.registerStaff(staff, type));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }



}
