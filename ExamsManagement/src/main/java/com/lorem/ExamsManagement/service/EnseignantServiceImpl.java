package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.EnseignantDAO;
import com.lorem.ExamsManagement.model.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantServiceImpl implements EnseignantService{

    @Autowired
    EnseignantDAO enseignantDAO;
    @Override
    public Optional<Enseignant> findEnseignantById(Long id) {
        return enseignantDAO.findById(id);
    }

    @Override
    public List<Enseignant> findAll() {
        return enseignantDAO.findAll();
    }

    @Override
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantDAO.save(enseignant);
    }
}
