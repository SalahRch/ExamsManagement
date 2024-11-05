package com.lorem.ExamsManagement.controller;

import com.lorem.ExamsManagement.model.ElementPedagogique;
import com.lorem.ExamsManagement.model.Enseignant;
import com.lorem.ExamsManagement.model.Module;
import com.lorem.ExamsManagement.service.ElementService;
import com.lorem.ExamsManagement.service.EnseignantService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EnseignantController {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ModuleService moduleService;

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("/enseignants/{id}")
    public ResponseEntity<Enseignant> getEnseignant(@PathVariable Long id) {
        return ResponseEntity.ok(enseignantService.findEnseignantById(id).get());
    }

    @PostMapping("/add/module")
    public ResponseEntity<Enseignant> addElement(@RequestParam("idElem") Long idElem , @RequestParam("idEns") Long idEns){
        Optional<Enseignant> enseignant = enseignantService.findEnseignantById(idEns);
        if(enseignant.isPresent()){
            enseignant.get().getElementPedagogiques().add(elementService.findElementById(idElem).get());
            return ResponseEntity.ok(enseignantService.saveEnseignant(enseignant.get()));
        }
        return ResponseEntity.notFound().build();

    }


    @GetMapping("/eligible/module/{id}")
    public ResponseEntity<?> getEligibleCoordinator(@PathVariable Long id) {
        Optional<Module> module = moduleService.findModuleById(id);
        List<Enseignant> enseignantList = enseignantService.findAll();
        if(module.isPresent()){
            List<Enseignant> eligibleEnseignants = enseignantList.stream()
                    .filter(enseignant -> enseignant.getElementPedagogiques().stream()
                            .anyMatch(element -> element.getModule().equals(module.get())))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(eligibleEnseignants);
        }
        return ResponseEntity.notFound().build();
    }

}
