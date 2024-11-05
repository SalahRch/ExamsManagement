package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.ExamDAO;
import com.lorem.ExamsManagement.model.Exam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExamServiceImpl implements ExamService{

    private final ExamDAO examDAO;

    public ExamServiceImpl(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examDAO.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examDAO.findAll();
    }

    @Override
    public Optional<Exam> findExamById(Long id) {
        return examDAO.findById(id);
    }

    @Override
    public void deleteExam(Exam exam) {
        examDAO.delete(exam);
    }

    @Override
    public Exam updateExam(Exam exam) {
        return examDAO.save(exam);
    }

    @Override
    public void deleteAll() {
        examDAO.deleteAll();
    }
}
