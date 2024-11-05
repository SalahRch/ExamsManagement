package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.ElementPedagogique;
import com.lorem.ExamsManagement.model.Module;
import com.lorem.ExamsManagement.DAO.ElementDAO;
import com.lorem.ExamsManagement.DAO.ModuleDAO;
import com.lorem.ExamsManagement.service.ElementService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/element")
@CrossOrigin(origins = "http://localhost:5173")
public class ElementController {

    @Autowired
    ElementService elementService;

    @Autowired
    ModuleService moduleService;




    @PostMapping("/add")
    public ResponseEntity<ElementPedagogique> addElement(@RequestBody ElementPedagogique elementPedagogique){
        return ResponseEntity.ok(elementService.saveElement(elementPedagogique));
    }


    @PostMapping("/addToModule")
    public ResponseEntity<ElementPedagogique> addElementToModule(@RequestParam Long idMod, @RequestParam Long idElem){

        Optional<Module> existingModule = moduleService.findModuleById(idMod);
        Optional<ElementPedagogique> elementPedagogique = elementService.findElementById(idElem);

        if(elementPedagogique.isPresent()){
            elementPedagogique.get().setModule(existingModule.get());
            elementPedagogique.get().setPartOfModule(true);
        }
        return ResponseEntity.ok(elementService.saveElement(elementPedagogique.get()));
    }
    @PostMapping("/removeFromModule")
    public ResponseEntity<ElementPedagogique> removeElementFromModule(@RequestParam Long idElem){
        Optional<ElementPedagogique> elementPedagogique = elementService.findElementById(idElem);
        if(elementPedagogique.isPresent()){
            elementPedagogique.get().setModule(null);
            elementPedagogique.get().setPartOfModule(false);
        }
        return ResponseEntity.ok(elementService.saveElement(elementPedagogique.get()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable Long id){
        Optional<ElementPedagogique> element = elementService.findElementById(id);
        if(element.isPresent()){
            elementService.deleteElement(element.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementPedagogique> findElementById(@PathVariable Long id){
        return  ResponseEntity.ok(elementService.findElementById(id).get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ElementPedagogique>> findAll(){
        return ResponseEntity.ok(elementService.retrieveAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ElementPedagogique> update(@PathVariable Long id, @RequestBody ElementPedagogique newElement){
        Optional<ElementPedagogique> element = elementService.findElementById(id);
        if(element.isPresent()){
            newElement.setId(id);
            newElement.setModule(element.get().getModule());
            return ResponseEntity.ok(elementService.saveElement(newElement));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<ElementPedagogique>> findElementByModule(@PathVariable Long moduleId){
        Optional<Module> module = moduleService.findModuleById(moduleId);
        return ResponseEntity.ok(module.get().getElementsPedagogiques());
    }

    @GetMapping("/getmodule/{elementId}")
    public ResponseEntity<Long> findModule(@PathVariable Long elementId){
        Optional<ElementPedagogique> elementPedagogique = elementService.findElementById(elementId);
        if(elementPedagogique.isPresent()){
            if(elementPedagogique.get().getModule() != null){
                return ResponseEntity.ok(elementPedagogique.get().getModule().getId());
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }







}
