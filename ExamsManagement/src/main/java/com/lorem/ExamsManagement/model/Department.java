package com.lorem.ExamsManagement.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Filiere> filiereList = new ArrayList<>();

    public Department(){

    }

    public Department(Long id, String name, List<Filiere> filiereList) {
        this.id = id;
        this.name = name;
        this.filiereList = filiereList;
    }

    public List<Filiere> getFiliereList() {
        return filiereList;
    }

    public void setFiliereList(List<Filiere> filiereList) {
        this.filiereList = filiereList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
