package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Filiere;
import com.lorem.ExamsManagement.model.FilliereType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilliereDAO extends JpaRepository<Filiere , Long> {

    Iterable<Filiere> findFiliereByFilliereType(FilliereType type);
}
