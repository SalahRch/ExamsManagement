package com.lorem.ExamsManagement.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff extends User{


    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;


    @Enumerated(value = EnumType.STRING)
    private StaffType staffType;



    public Staff(){

    }
    public Staff(Long id, String nom, String prenom, String username, String password, StaffType staffType) {
        super(id, username, password, Type.STAFF);
        this.nom= nom;
        this.prenom = prenom;
        this.staffType = staffType;
    }

    @Override
    public Type getType(){
        return Type.STAFF;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {

        this.staffType = staffType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", staffType=" + staffType +
                '}';
    }
}
