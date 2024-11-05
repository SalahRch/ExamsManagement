package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleDAO extends JpaRepository<Module, Long> {
}
