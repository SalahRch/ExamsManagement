package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.Exam;
import com.lorem.ExamsManagement.model.ExamRoom;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    Exam saveExam(Exam exam);
    List<Exam> findAll();
    Optional<Exam> findExamById(Long id);
    void deleteExam(Exam exam);
    Exam updateExam(Exam exam);

    void deleteAll();
}
