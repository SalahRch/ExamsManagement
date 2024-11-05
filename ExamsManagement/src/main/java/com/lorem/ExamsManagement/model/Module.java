package com.lorem.ExamsManagement.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long id;

    private String title;



    @ManyToMany(mappedBy = "modules")
    @JsonIgnore
    private List<Filiere> filieres;



    @OneToMany(mappedBy = "module")
    private List<ElementPedagogique> elementsPedagogiques;


    @JsonBackReference(value="module-coordinator")
    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Enseignant coordinator;




    public Module(){
        this.elementsPedagogiques = new ArrayList<>();
        this.filieres = new ArrayList<>();
    }

    public Module(Long id, List<Filiere> filieres, List<ElementPedagogique> elementsPedagogiques, Enseignant coordinator, String title) {
        this.id = id;
        this.filieres = filieres;
        this.elementsPedagogiques = elementsPedagogiques;
        this.coordinator = coordinator;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filieres=" + filieres +
                ", elementsPedagogiques=" + elementsPedagogiques +
                ", coordinator=" + coordinator +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enseignant getCoordinator() {
        return coordinator;
    }


    public void setCoordinator(Enseignant coordinator) {
        this.coordinator = coordinator;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public List<ElementPedagogique> getElementsPedagogiques() {
        return elementsPedagogiques;
    }

    public void setElementsPedagogiques(List<ElementPedagogique> elementsPedagogiques) {
        this.elementsPedagogiques = elementsPedagogiques;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void removeCoordinator() {
        // Hypothetical cleanup operations
        //this.coordinator.notifyRemovalFromModule(this);
        this.coordinator = null;
       //updateCoordinatorCaches();
        //triggerCoordinatorRemovalBusinessLogic();
    }


    public void addElement(ElementPedagogique elementPedagogique) {
        this.elementsPedagogiques.add(elementPedagogique);
    }
}
