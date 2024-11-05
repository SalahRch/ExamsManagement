package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.ModuleDAO;
import com.lorem.ExamsManagement.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModuleServiceImpl implements ModuleService{

    @Autowired
    ModuleDAO moduleDAO;
    @Override
    public Module saveModule(Module module) {
        return moduleDAO.save(module);
    }

    @Override
    public Optional<Module> findModuleById(Long id) {
        return moduleDAO.findById(id);
    }

    @Override
    public void deleteModule(Module module) {
        moduleDAO.delete(module);
    }

    @Override
    public List<Module> retrieveAll() {
        return moduleDAO.findAll();
    }
}
