package com.lorem.ExamsManagement.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "filiere")
public class Filiere {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "filiere_id")
    private Long id;

    private String description;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @OneToMany(mappedBy = "filiere")
    private List<EnseignantGroup> enseignantGroups;

    @OneToOne
    @JoinColumn(name = "coordinator_id")
    @JsonIgnore
    private Enseignant coordinatedFiliere;




    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "filiere_module",
            joinColumns = @JoinColumn(name = "filiere_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private List<Module> modules;


    @Enumerated(value = EnumType.STRING)
    private Niveau niveau;

    @Enumerated(value = EnumType.STRING)
    private FilliereType filliereType;

    public Filiere(){

    }

    public Filiere(Long id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public Filiere(Long id, String description, String name,FilliereType filliereType, Niveau niveau) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.filliereType = filliereType;
        this.niveau = niveau;
    }

    public Filiere(Long id, String description, String name, Department department, List<EnseignantGroup> enseignantGroups, Enseignant coordinatedFiliere, List<Module> modules) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.department = department;
        this.enseignantGroups = enseignantGroups;
        this.coordinatedFiliere = coordinatedFiliere;
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<EnseignantGroup> getEnseignantGroups() {
        return enseignantGroups;
    }

    public void setEnseignantGroups(List<EnseignantGroup> enseignantGroups) {
        this.enseignantGroups = enseignantGroups;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Enseignant getCoordinatedFiliere() {
        return coordinatedFiliere;
    }

    public void setCoordinatedFiliere(Enseignant coordinatedFiliere) {
        this.coordinatedFiliere = coordinatedFiliere;
    }

    public FilliereType getFilliereType() {
        return filliereType;
    }

    public void setFilliereType(FilliereType filliereType) {
        this.filliereType = filliereType;
    }

    public List<Niveau> getValidLevels() {
        if (filliereType == FilliereType.CP) {
            return Arrays.asList(Niveau.FIRST_YEAR, Niveau.SECOND_YEAR);
        } else {
            return Arrays.asList(Niveau.FIRST_YEAR, Niveau.SECOND_YEAR, Niveau.THIRD_YEAR);
        }
    }
    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
