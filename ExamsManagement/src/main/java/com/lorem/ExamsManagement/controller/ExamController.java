package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.Exam;
import com.lorem.ExamsManagement.service.EnseignantService;
import com.lorem.ExamsManagement.service.ExamService;
import com.lorem.ExamsManagement.service.FiliereService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private FiliereService filiereService;
    @Autowired
    private EnseignantService enseignantService;

    @PostMapping
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam, @RequestParam Long moduleId, @RequestParam Long filiereId, @RequestParam Long coordinatorId) {
        exam.setModule(moduleService.findModuleById(moduleId).get());
        System.out.println(exam.getModule());
        exam.setFiliere(filiereService.findFiliereById(filiereId).get());
        System.out.println(exam.getFiliere());
        exam.setCoordinator(enseignantService.findEnseignantById(coordinatorId).get());
        System.out.println(exam);

        return ResponseEntity.ok(examService.saveExam(exam));
    }

    @PostMapping("/file-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            String path = "C:\\Users\\lorem\\Downloads\\ExamsManagement\\ExamsManagement\\src\\main\\resources\\static\\" + file.getOriginalFilename();

            File dest = new File(path);

            file.transferTo(dest);


            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> saveExam(@PathVariable Long id) {
        Exam exam = examService.findExamById(id).get();
        examService.deleteExam(exam);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllExams() {
        examService.deleteAll();
        return ResponseEntity.ok().build();
    }
    @GetMapping("/dates")
    public ResponseEntity<List<LocalDate>> getDatesBySlot(@RequestParam String timeSLot) {
        List<LocalDate> dates = new ArrayList<>();
        for(Exam exam : examService.findAll()) {
            if(exam.getTimeSlot().name().equals(timeSLot)) {
                dates.add(exam.getDate());
            }
        }
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/dates/all")
    public ResponseEntity<List<LocalDate>> getDates() {
        List<LocalDate> dates = new ArrayList<>();
        for(Exam exam : examService.findAll()) {
            dates.add(exam.getDate());
        }
        return ResponseEntity.ok(dates);
    }
    @GetMapping("/get-exams")
    public ResponseEntity<List<Exam>> getExams(@RequestParam LocalDate date, @RequestParam String timeslot) {
       List<Exam> exams = new ArrayList<>();
        for(Exam exam : examService.findAll()) {
            if(exam.getDate().equals(date) && exam.getTimeSlot().name().equals(timeslot)) {
                exams.add(exam);
            }
        }
        return ResponseEntity.ok(exams);
    }
}
