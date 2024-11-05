package com.lorem.ExamsManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "element_pedagogique")
public class ElementPedagogique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id")
    private Long id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "elementPedagogiques")
    @JsonIgnore
    private List<Enseignant> enseignants;


    private boolean isPartOfModule;


    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonIgnore
    private Module module;

    public ElementPedagogique(){

    }

    public ElementPedagogique(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
       this.isPartOfModule = false;
    }

    public ElementPedagogique(Long id, String title, String description, Module module) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.module = module;
        this.isPartOfModule = true;
    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Module getModule() {
        return module;
    }

    public boolean isPartOfModule() {
        return isPartOfModule;
    }

    public void setPartOfModule(boolean partOfModule) {
        isPartOfModule = partOfModule;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
