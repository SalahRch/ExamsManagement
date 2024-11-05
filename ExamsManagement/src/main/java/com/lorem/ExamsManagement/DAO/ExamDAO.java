package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDAO extends JpaRepository<Exam, Long>{

}
