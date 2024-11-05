package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.ExamRoomDAO;
import com.lorem.ExamsManagement.model.Exam;
import com.lorem.ExamsManagement.model.ExamRoom;
import com.lorem.ExamsManagement.model.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ExamRoomServiceImpl implements ExamRoomService {
    private final ExamRoomDAO examRoomDAO;
    private final ExamService examService;

    public ExamRoomServiceImpl(ExamRoomDAO examRoomDAO, ExamService examService) {
        this.examRoomDAO = examRoomDAO;
        this.examService = examService;
    }

    @Override
    public ExamRoom saveExamRoom(ExamRoom examRoom) {
        return examRoomDAO.save(examRoom);
    }


    @Override
    public List<ExamRoom> findAll() {
        return examRoomDAO.findAll();
    }

    @Override
    public Optional<ExamRoom> findExamRoomById(Long id) {
        return examRoomDAO.findById(id);
    }

    @Override
    public void deleteExamRoom(Long id) {
        examRoomDAO.deleteById(id);
    }

    @Override
    public ExamRoom updateExamRoom(ExamRoom examRoom) {
        return examRoomDAO.save(examRoom);
    }


    @Override
    public Map<LocalTime, LocalTime> getAvailableSlotsAN(Long roomId, LocalDate date, int hours) {
        return getAvailableSlotsForTimeSlot(roomId, date, TimeSlot.AN, hours );
    }

    @Override
    public Map<LocalTime, LocalTime> getAvailableSlotsFN(Long roomId, LocalDate date, int hours) {
        return getAvailableSlotsForTimeSlot(roomId, date, TimeSlot.FN, hours);
    }

    @Override
    public Map<LocalTime, LocalTime> getAvailableSlotsForTimeSlot(Long roomId, LocalDate date, TimeSlot timeSlot, int hours) {
        ExamRoom examRoom = findExamRoomById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Map<LocalTime, LocalTime> occupiedSlots = examRoom.getExams().stream()
                .filter(exam -> exam.getDate().equals(date) && exam.getTimeSlot() == timeSlot)
                .collect(Collectors.toMap(
                        Exam::getStartTime,
                        exam -> exam.getStartTime().plus(exam.getExpectedDuration())
                ));
        System.out.println("occupied slots" +occupiedSlots);
        LocalTime startTime = timeSlot.getStartTime();
        LocalTime endTime = timeSlot.getEndTime();
        Map<LocalTime, LocalTime> availableSlots = new LinkedHashMap<>();
        while (startTime.isBefore(endTime)) {
            LocalTime finalStartTime = startTime;
            boolean isOccupied = occupiedSlots.entrySet().stream()
                    .anyMatch(entry -> (finalStartTime.equals(entry.getKey()) || finalStartTime.isAfter(entry.getKey())) && finalStartTime.isBefore(entry.getValue())
                            || (finalStartTime.plusHours(hours).equals(entry.getKey()) || finalStartTime.plusHours(hours).isAfter(entry.getKey())) && finalStartTime.plusHours(hours).isBefore(entry.getValue()));
            if (!isOccupied && (startTime.plusHours(hours).isBefore(endTime) || startTime.plusHours(hours).equals(endTime))) {
                availableSlots.put(startTime, startTime.plusHours(hours));
            }
            startTime = startTime.plusMinutes(30);  // adjust this value to change the interval between slots
        }
        return availableSlots;
    }


    @Override
    public void addSlot(Long roomId, Long examId) {
        ExamRoom examRoom = findExamRoomById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Exam exam = examService.findExamById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        exam.getExamRooms().add(examRoom);
        examRoom.getExams().add(exam);
        saveExamRoom(examRoom);
        examService.saveExam(exam);
    }

}
