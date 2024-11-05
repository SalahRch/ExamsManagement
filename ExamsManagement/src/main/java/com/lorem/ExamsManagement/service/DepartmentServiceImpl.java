package com.lorem.ExamsManagement.service;


import com.lorem.ExamsManagement.DAO.DepartementDAO;
import com.lorem.ExamsManagement.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartementDAO departementDAO;
    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departementDAO.findById(id);
    }
}
