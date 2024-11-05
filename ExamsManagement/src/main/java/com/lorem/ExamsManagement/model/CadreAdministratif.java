package com.lorem.ExamsManagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;



@Entity
public class CadreAdministratif extends Staff{


    @ManyToMany
    @JoinTable(
            name = "absence_examroom",
            joinColumns = @JoinColumn(name = "cadre_id"),
            inverseJoinColumns = @JoinColumn(name = "examroom_id"))
    private List<ExamRoom> absenceControlledExams;




    public CadreAdministratif(){
        this.absenceControlledExams = new ArrayList<>();
    }

    public CadreAdministratif(Long id, String nom, String prenom, String username, String password) {
        super(id, nom, prenom, username, password, StaffType.CADRE_ADMINISTRATIF);
    }

    public List<ExamRoom> getAbsenceControlledExams() {
        return absenceControlledExams;
    }

    public void setAbsenceControlledExams(List<ExamRoom> absenceControlledExams) {
        this.absenceControlledExams = absenceControlledExams;
    }

    @Override
    public StaffType getStaffType() {
        return StaffType.CADRE_ADMINISTRATIF;
    }

}
