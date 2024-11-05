package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.Exam;
import com.lorem.ExamsManagement.model.ExamRoom;
import com.lorem.ExamsManagement.model.TimeSlot;
import com.lorem.ExamsManagement.service.ExamRoomService;
import com.lorem.ExamsManagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/examRoom")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamRoomController {

    @Autowired
    private ExamRoomService examRoomService;
    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamRoom> saveExamRoom(@RequestBody ExamRoom examRoom) {
        return ResponseEntity.ok(examRoomService.saveExamRoom(examRoom));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExamRoom>> findAll() {
        return ResponseEntity.ok(examRoomService.findAll());
    }


    @PostMapping("/addSlot/{roomId}/{examId}")
    public ResponseEntity<?> addSlot(@PathVariable Long roomId, @PathVariable Long examId){
        Exam exam = examService.findExamById(examId).get();
        Map <LocalTime, LocalTime> availableSlots ;
        if(exam.getTimeSlot().name().equals("AN")){
            availableSlots = examRoomService.getAvailableSlotsAN(roomId, exam.getDate(), (int) exam.getActualDuration().toHours());
        } else{
            availableSlots = examRoomService.getAvailableSlotsFN(roomId, exam.getDate(), (int) exam.getActualDuration().toHours());
        }
        System.out.println("exam duration ->"+exam.getActualDuration().toHours());
        //iterate over the available slots and add the exam if the slot is available
        for(Map.Entry<LocalTime, LocalTime> entry : availableSlots.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            if ((entry.getKey().isBefore(exam.getStartTime()) || entry.getKey().equals(exam.getStartTime()))
                    && (entry.getValue().isAfter(exam.getStartTime().plus(exam.getActualDuration())) || entry.getValue().equals(exam.getStartTime().plus(exam.getActualDuration())))) {
                examRoomService.addSlot(roomId, examId);
                System.out.println("possible one is" +entry.getKey() + " " + entry.getValue());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not possible to add the exam at this time");
    }


    @GetMapping("/availableSlots/{roomId}/{date}/{timeslot}/{duration}")
    public ResponseEntity<Map<LocalTime, LocalTime>> getAvailableSlots(@PathVariable Long roomId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable String timeslot, @PathVariable int duration) {
        if(timeslot.equals("AN")){
            return ResponseEntity.ok(examRoomService.getAvailableSlotsAN(roomId, date, duration));
        }
        else{
            return ResponseEntity.ok(examRoomService.getAvailableSlotsFN(roomId, date, duration));
        }
    }



}
