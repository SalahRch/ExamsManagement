package com.lorem.ExamsManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "enseignant")
public class Enseignant  extends Staff{


    @ManyToMany
    @JoinTable(
            name = "enseignant_element",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "element_id")
    )
    @JsonIgnore
    private List<ElementPedagogique> elementPedagogiques;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;


    @ManyToOne
    @JoinColumn(name = "enseignant_group_id")
    private EnseignantGroup enseignantGroup;

    @ManyToMany(mappedBy = "surveillants")
    private List<Exam> exams;




    @JsonManagedReference(value="module-coordinator")
    @OneToMany(mappedBy = "coordinator")
    private List<Module> coordinatedModules;

    @OneToOne(mappedBy = "coordinatedFiliere")
    private Filiere coordinatedFiliere;

    public Enseignant(){

    }

    public Enseignant(Long id, String nom, String prenom, String username, String password, byte[] profilePicture) {
        super(id, nom, prenom, username, password, StaffType.ENSEIGNANT);
        this.profilePicture = profilePicture;
    }

    public Enseignant(Long id, String nom, String prenom, String username, String password){
        super(id, nom, prenom, username, password, StaffType.ENSEIGNANT);
    }
    public Enseignant(List<ElementPedagogique> elements, EnseignantGroup enseignantGroup, List<ExamRoom> surveilledexams, List<Module> coordinatedModules, Filiere coordinatedFiliere) {
        this.elementPedagogiques = elements;
        this.enseignantGroup = enseignantGroup;

        this.coordinatedModules = coordinatedModules;
        this.coordinatedFiliere = coordinatedFiliere;
    }

    public Enseignant(List<ElementPedagogique> elementPedagogiques, byte[] profilePicture, EnseignantGroup enseignantGroup, List<Module> coordinatedModules, Filiere coordinatedFiliere) {
        this.elementPedagogiques = elementPedagogiques;
        this.profilePicture = profilePicture;
        this.enseignantGroup = enseignantGroup;
        this.coordinatedModules = coordinatedModules;
        this.coordinatedFiliere = coordinatedFiliere;
    }

    public Enseignant(Long id, String nom, String prenom, String username, String password, StaffType staffType, List<ElementPedagogique> elementPedagogiques, byte[] profilePicture, EnseignantGroup enseignantGroup, List<Module> coordinatedModules, Filiere coordinatedFiliere) {
        super(id, nom, prenom, username, password, staffType);
        this.elementPedagogiques = elementPedagogiques;
        this.profilePicture = profilePicture;
        this.enseignantGroup = enseignantGroup;
        this.coordinatedModules = coordinatedModules;
        this.coordinatedFiliere = coordinatedFiliere;
    }

    public List<ElementPedagogique> getElementPedagogiques() {
        return elementPedagogiques;
    }

    public void setElementPedagogiques(List<ElementPedagogique> elementPedagogiques) {
        this.elementPedagogiques = elementPedagogiques;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }





    @Override
    public StaffType getStaffType() {
        return StaffType.ENSEIGNANT;
    }


    public Filiere getCoordinatedFiliere() {
        return coordinatedFiliere;
    }

    public void setCoordinatedFiliere(Filiere coordinatedFiliere) {
        this.coordinatedFiliere = coordinatedFiliere;
    }
    public List<Module> getCoordinatedModules() {
        return coordinatedModules;
    }

    public void setCoordinatedModules(List<Module> coordinatedModules) {
        this.coordinatedModules = coordinatedModules;
    }

    public EnseignantGroup getEnseignantGroup() {
        return enseignantGroup;
    }

    public void setEnseignantGroup(EnseignantGroup enseignantGroup) {
        this.enseignantGroup = enseignantGroup;
    }
}
