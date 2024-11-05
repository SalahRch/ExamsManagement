package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.DAO.EnseignantDAO;
import com.lorem.ExamsManagement.DAO.StaffDAO;
import com.lorem.ExamsManagement.model.*;
import com.lorem.ExamsManagement.DAO.ModuleDAO;
import com.lorem.ExamsManagement.model.Module;
import com.lorem.ExamsManagement.service.ElementService;
import com.lorem.ExamsManagement.service.EnseignantService;
import com.lorem.ExamsManagement.service.FiliereService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:5173")
public class ModuleController {

    @Autowired
    ModuleService moduleService;
    @Autowired
    EnseignantService enseignantService;
    @Autowired
    FiliereService filiereService ;
    @Autowired
    ElementService elementService;


    @PostMapping("/add")
    public ResponseEntity<Module> addModule(@RequestBody Module module){
        return ResponseEntity.ok(moduleService.saveModule(module));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> findElementById(@PathVariable Long id){
        return  ResponseEntity.ok(moduleService.findModuleById(id).get());
    }

    @GetMapping("/elements/{id}")
    public ResponseEntity<List<ElementPedagogique>> retrieveElements(@PathVariable Long id){
        Optional<Module> Module = moduleService.findModuleById(id);
        if(Module.isPresent()){
            return ResponseEntity.ok(Module.get().getElementsPedagogiques());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id){
        Optional<Module> module = moduleService.findModuleById(id);
        for(ElementPedagogique element : module.get().getElementsPedagogiques()){
            element.setModule(null);
            element.setPartOfModule(false);
            elementService.saveElement(element);
        }
        for(Filiere filiere : module.get().getFilieres()){
            filiere.getModules().removeIf(module1 -> module1 == module.get());
            filiereService.saveFiliere(filiere);
        }
        moduleService.deleteModule(module.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module newModule){
        Optional<Module> module = moduleService.findModuleById(id);
        if(module.isPresent()){
            newModule.setCoordinator(module.get().getCoordinator());
            newModule.setId(id);
            System.out.println(newModule);
            return ResponseEntity.ok(moduleService.saveModule(newModule));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addCoordinator")
    public ResponseEntity<Module> assignCoordinator(@RequestParam Long idCord, @RequestParam Long idMod){
        Optional<Module> module = moduleService.findModuleById(idMod);
        Optional<Enseignant> enseignant = enseignantService.findEnseignantById(idCord);
        System.out.println(enseignant);
        System.out.println(module);
        if(enseignant.isPresent()){
            module.get().setCoordinator(enseignant.get());
        }
        return ResponseEntity.ok(moduleService.saveModule(module.get()));
    }


    @PostMapping("/removeCoordinator/{id}")
    public ResponseEntity<?> removeCoordinator(@PathVariable Long id){
        Optional<Module> module = moduleService.findModuleById(id);
        if(module.isPresent()){
            module.get().removeCoordinator();
            return ResponseEntity.ok(moduleService.saveModule(module.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module with id " + id + " not found");
    }

    @GetMapping("/retrieveCoordinator/{id}")
    public ResponseEntity<?> retrieveCoordinator(@PathVariable Long id){
        Optional<Module> module = moduleService.findModuleById(id);
        if(module.get().getCoordinator() != null){
            return ResponseEntity.ok(module.get().getCoordinator());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module with id " + id + " not found");
    }






    @GetMapping("/filieres/{id}")
    public ResponseEntity<List<Filiere>> retriveFilieres(@PathVariable Long id){
        Optional<Module> module = moduleService.findModuleById(id);
        if(module.isPresent()){
            return ResponseEntity.ok(module.get().getFilieres());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<Module>> retrieveAll(){
        return ResponseEntity.ok(moduleService.retrieveAll());
    }





}
