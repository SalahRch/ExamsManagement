package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.ElementPedagogique;
import com.lorem.ExamsManagement.model.Module;

import java.util.List;
import java.util.Optional;

public interface ElementService {
    ElementPedagogique saveElement(ElementPedagogique elementPedagogique);
    Optional<ElementPedagogique> findElementById(Long id);
    void deleteElement(ElementPedagogique elementPedagogique);

    List<ElementPedagogique> retrieveAll();
}
