package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.FilliereDAO;
import com.lorem.ExamsManagement.model.Filiere;
import com.lorem.ExamsManagement.model.FilliereType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereServiceImpl implements FiliereService{
    @Autowired
    FilliereDAO filliereDAO;
    @Override
    public Filiere saveFiliere(Filiere filiere) {
            return filliereDAO.save(filiere);
    }

    @Override
    public Optional<Filiere> findFiliereById(Long id) {
        return filliereDAO.findById(id);
    }

    @Override
    public void deleteFiliere(Filiere filiere) {
        filliereDAO.delete(filiere);
    }

    @Override
    public List<Filiere> retrieveAll() {
        return filliereDAO.findAll();
    }

    @Override
    public Iterable<Filiere> findFilliereByType(FilliereType type) {
        return filliereDAO.findFiliereByFilliereType(type);
    }
}
