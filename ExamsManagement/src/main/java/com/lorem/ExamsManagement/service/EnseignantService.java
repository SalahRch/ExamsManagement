package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.ElementPedagogique;
import com.lorem.ExamsManagement.model.Enseignant;

import java.util.List;
import java.util.Optional;

public interface EnseignantService {
    Optional<Enseignant> findEnseignantById(Long id);
    List<Enseignant> findAll();
    Enseignant saveEnseignant(Enseignant enseignant);
}
