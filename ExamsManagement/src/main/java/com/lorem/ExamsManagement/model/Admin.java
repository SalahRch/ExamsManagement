package com.lorem.ExamsManagement.model;


import jakarta.persistence.Entity;

@Entity
public class Admin extends User{


    public Admin(){

    }

    public Admin(Long id, String username, String password) {
        super(id, username, password, Type.ADMINISTRATEUR);

    }

    @Override
    public Type getType() {
        return Type.ADMINISTRATEUR;
    }



}
