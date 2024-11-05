package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.Filiere;
import com.lorem.ExamsManagement.model.FilliereType;
import com.lorem.ExamsManagement.model.Module;

import java.util.List;
import java.util.Optional;

public interface FiliereService {


    Filiere saveFiliere(Filiere filiere);
    Optional<Filiere> findFiliereById(Long id);
    void deleteFiliere(Filiere filiere);

    List<Filiere> retrieveAll();

    Iterable<Filiere> findFilliereByType(FilliereType type);
}
