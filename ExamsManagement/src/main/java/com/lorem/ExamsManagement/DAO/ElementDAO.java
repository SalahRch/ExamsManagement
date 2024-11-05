package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.ElementPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementDAO extends JpaRepository<ElementPedagogique, Long> {

}
