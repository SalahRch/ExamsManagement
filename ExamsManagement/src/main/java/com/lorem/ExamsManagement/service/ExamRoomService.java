package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.ExamRoom;
import com.lorem.ExamsManagement.model.TimeSlot;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ExamRoomService {
    ExamRoom saveExamRoom(ExamRoom examRoom);
    List<ExamRoom> findAll();
    Optional<ExamRoom> findExamRoomById(Long id);
    void deleteExamRoom(Long id);
    ExamRoom updateExamRoom(ExamRoom examRoom);

    Map<LocalTime, LocalTime> getAvailableSlotsAN(Long roomId, LocalDate date, int hours);

    Map<LocalTime, LocalTime> getAvailableSlotsFN(Long roomId, LocalDate date, int hours);


    Map<LocalTime, LocalTime> getAvailableSlotsForTimeSlot(Long roomId, LocalDate date, TimeSlot timeSlot, int hours);

    void addSlot(Long roomId, Long examId);


}
