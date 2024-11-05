package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.ElementDAO;
import com.lorem.ExamsManagement.model.ElementPedagogique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ElementServiceImpl implements ElementService{


    @Autowired
    ElementDAO elementDAO;


    @Override
    public ElementPedagogique saveElement(ElementPedagogique elementPedagogique) {
        return elementDAO.save(elementPedagogique);
    }

    @Override
    public Optional<ElementPedagogique> findElementById(Long id) {
        return elementDAO.findById(id);
    }

    @Override
    public void deleteElement(ElementPedagogique elementPedagogique) {
        elementDAO.delete(elementPedagogique);
    }

    @Override
    public List<ElementPedagogique> retrieveAll() {
        return elementDAO.findAll();
    }
}
