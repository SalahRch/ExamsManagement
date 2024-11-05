package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {
    Module saveModule(Module module);
    Optional<Module> findModuleById(Long id);
    void deleteModule(Module module);

    List<Module> retrieveAll();
}
