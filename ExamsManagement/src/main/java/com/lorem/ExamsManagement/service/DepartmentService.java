package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.Department;

import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findDepartmentById(Long id);
}
