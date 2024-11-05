package com.lorem.ExamsManagement.security.authentication;

import com.lorem.ExamsManagement.DAO.StaffDAO;
import com.lorem.ExamsManagement.model.*;
import com.lorem.ExamsManagement.DAO.UserDAO;
import com.lorem.ExamsManagement.security.config.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private jwtService JwtService;
    @Autowired
    private StaffDAO staffDAO;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse registerAdmin(Admin request){
        request.setType(request.getType());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userDAO.save( request);
        String token = JwtService.generateToken(request , generateExtraClaims(request));
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse registerStaff(Staff request, String type){
        if(type.equals("enseignant")){
            Enseignant enseignant = new Enseignant(null,request.getNom(),request.getPrenom(),request.getUsername(), request.getPassword());
            enseignant.setPassword(passwordEncoder.encode(enseignant.getPassword()));
            staffDAO.save(enseignant);
        } else if(type.equals("cadre")){
            CadreAdministratif cadreAdministratif = new CadreAdministratif(null, request.getNom(), request.getPrenom(),request.getUsername(), request.getPassword());
            cadreAdministratif.setPassword(passwordEncoder.encode(cadreAdministratif.getPassword()));
            staffDAO.save(cadreAdministratif);
        } else {
            throw new IllegalArgumentException("Invalid staff type");
        }
        String token = JwtService.generateToken(request , generateExtraClaims(request));
        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse login(AuthenticationRequest request){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        authenticationManager.authenticate(token);
        User user = userDAO.findByUsername(request.getUsername()).get();

        String jwt = JwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);

    }

    private Map<String , Object> generateExtraClaims(User user) {
        Map<String , Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("role",user.getType().name());
        System.out.println(extraClaims);

        return extraClaims;
    }

}
