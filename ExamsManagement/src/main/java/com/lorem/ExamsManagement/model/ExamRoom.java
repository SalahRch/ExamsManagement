package com.lorem.ExamsManagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * . Le système assigne à chaque salle d'examen les surveillants et les
 * contrôleurs d'absence en respectant les préférences choisies par
 * l'utilisateur
 */
@Entity
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "examRooms")
    private List<Exam> exams;
    private String name;
    private int capacity;

    @ManyToMany(mappedBy = "absenceControlledExams")
    private List<CadreAdministratif> absenceControllers;



    public ExamRoom() {
        this.absenceControllers = new ArrayList<>();
    }

    public ExamRoom(Long id, String name, int capacity, List<Exam> exams, List<CadreAdministratif> absenceControllers) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.exams = exams;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<CadreAdministratif> getAbsenceControllers() {
        return absenceControllers;
    }

    public void setAbsenceControllers(List<CadreAdministratif> absenceControllers) {
        this.absenceControllers = absenceControllers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }


}
