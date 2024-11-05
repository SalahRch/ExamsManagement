package com.lorem.ExamsManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Entity
@Table(name = "exam")
public class Exam {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "coordinator_id")
        private Enseignant coordinator;

        @ManyToMany
        @JoinTable(
                name = "exam_surveillants",
                joinColumns = @JoinColumn(name = "exam_id"),
                inverseJoinColumns = @JoinColumn(name = "surveillant_id")
        )
        private List<Enseignant> surveillants;

        @ManyToOne
        @JoinColumn(name = "module_id")
        private Module module;

        @ManyToOne
        @JoinColumn(name = "filiere_id")
        private Filiere filiere;

        @Enumerated(EnumType.STRING)
        private Semestre semester;

        @Enumerated(EnumType.STRING)
        private Session session;

        @Enumerated(EnumType.STRING)
        private ExamType type;


        @Enumerated(EnumType.STRING)
        private TimeSlot timeSlot;
        private LocalDate date;
        private LocalTime startTime;
        private Duration expectedDuration;
        private Duration actualDuration;
    @ManyToMany
    @JoinTable(
            name = "exam_examroom",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "examroom_id"))
    @JsonIgnore
    private List<ExamRoom> examRooms;
      private String academicYear;
      private String examPaper;
      private File report;
      private String textReport;

      public Exam(){

      }



    public Exam(Long id, Enseignant coordinator, List<Enseignant> surveillants, Module module, Filiere filiere, Semestre semester, Session session, ExamType type, TimeSlot timeSlot, LocalDate date, LocalTime startTime, Duration expectedDuration, Duration actualDuration, List<ExamRoom> examRooms, String academicYear, String examPaper, File report, String textReport) {
        this.id = id;
        this.coordinator = coordinator;
        this.surveillants = surveillants;
        this.module = module;
        this.filiere = filiere;
        this.semester = semester;
        this.session = session;
        this.type = type;
        this.timeSlot = timeSlot;
        this.date = date;
        this.startTime = startTime;
        this.expectedDuration = expectedDuration;
        this.actualDuration = actualDuration;
        this.examRooms = examRooms;
        this.academicYear = academicYear;
        this.examPaper = examPaper;
        this.report = report;
        this.textReport = textReport;
    }

    public List<ExamRoom> getExamRooms() {
        return examRooms;
    }

    public void setExamRooms(List<ExamRoom> examRooms) {
        this.examRooms = examRooms;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enseignant getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Enseignant coordinator) {
        this.coordinator = coordinator;
    }

    public List<Enseignant> getSurveillants() {
        return surveillants;
    }

    public void setSurveillants(List<Enseignant> surveillants) {
        this.surveillants = surveillants;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Semestre getSemester() {
        return semester;
    }

    public void setSemester(Semestre semester) {
        this.semester = semester;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ExamType getType() {
        return type;
    }

    public void setType(ExamType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(Duration expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public Duration getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(Duration actualDuration) {
        this.actualDuration = actualDuration;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(String examPaper) {
        this.examPaper = examPaper;
    }

    public File getReport() {
        return report;
    }

    public void setReport(File report) {
        this.report = report;
    }

    public String getTextReport() {
        return textReport;
    }

    public void setTextReport(String textReport) {
        this.textReport = textReport;
    }
}

