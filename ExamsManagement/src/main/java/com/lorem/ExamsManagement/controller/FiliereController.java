package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.*;
import com.lorem.ExamsManagement.model.Module;
import com.lorem.ExamsManagement.service.DepartmentService;
import com.lorem.ExamsManagement.service.FiliereService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filiere")
@CrossOrigin(origins = "http://localhost:5173")
public class FiliereController {

    @Autowired
    FiliereService filiereService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ModuleService moduleService;

    @PostMapping("/add")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere){
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFiliere(@RequestParam Long id){
        filiereService.deleteFiliere(filiereService.findFiliereById(id).get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Filiere>> getAllFilieres(){
        return ResponseEntity.ok(filiereService.retrieveAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.findFiliereById(id).get());
    }

    @GetMapping("/byType")
    public ResponseEntity<Iterable<Filiere>> getFiliereByType(@RequestParam String type){
        return ResponseEntity.ok(filiereService.findFilliereByType(FilliereType.valueOf(type)));
    }

    @GetMapping("/modules/{id}")
    public ResponseEntity<Iterable<Module>> getModules(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.findFiliereById(id).get().getModules());
    }


    @PostMapping("/addDepartment")
    public ResponseEntity<Filiere> addDepartment(@RequestParam Long idFiliere, @RequestParam Long idDepartment){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        Department department = departmentService.findDepartmentById(idDepartment).get();
        filiere.setDepartment(department);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @PostMapping("/removeDepartment")
    public ResponseEntity<Filiere> removeDepartment(@RequestParam Long idFiliere){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        filiere.setDepartment(null);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @PostMapping("/addModule")
    public ResponseEntity<Filiere> addModule(@RequestParam Long idFiliere, @RequestParam Long idModule){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        Module module = moduleService.findModuleById(idModule).get();
        filiere.getModules().add(module);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @GetMapping("/coordinator/{id}")
    public ResponseEntity<Enseignant> getCoordinator(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.findFiliereById(id).get().getCoordinatedFiliere());
    }

    @GetMapping("/enseignants/{id}")
    public ResponseEntity<Iterable<Enseignant>> getEnseignants(@PathVariable Long id){
       //iterate over modules and get the enseignants
        Optional<Filiere> filliere = filiereService.findFiliereById(id);
        List<Enseignant> enseignants = new ArrayList<>();
        if(filliere.isPresent()){
            for(Module module : filliere.get().getModules()){
                for(ElementPedagogique element : module.getElementsPedagogiques()){
                    enseignants.addAll(element.getEnseignants());
                }
            }
        }
        return ResponseEntity.ok(enseignants);
    }

    /***
     *     @GetMapping("/valid-levels/{type}")
     *     public ResponseEntity<List<Niveau>> getValidLevels(@PathVariable String type) {
     *         FilliereType typeFiliere;
     *         try {
     *             typeFiliere = FilliereType.valueOf(type.toUpperCase());
     *         } catch (IllegalArgumentException e) {
     *             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     *         }
     *         Filiere filiere = new Filiere();
     *         filiere.setFilliereType(typeFiliere);
     *         return ResponseEntity.ok(filiere.getValidLevels());
     *     }
     */
}
