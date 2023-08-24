package com.rang.scheduler.controllers;


import com.rang.scheduler.entities.Schedule;

import com.rang.scheduler.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/add")
    public ResponseEntity<Schedule> addData(@RequestBody Schedule data) {

        Schedule schedule = scheduleRepository.save(data);

        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Schedule> getDataById(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.getReferenceById(id);

        if (schedule != null) {

            return ResponseEntity.ok(schedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Schedule>> getAllData() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Schedule> updateData(@PathVariable Long id, @RequestBody Schedule newData) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isPresent()) {
            Schedule updateSchedule = optionalSchedule.get();
            scheduleRepository.save(updateSchedule);
            return ResponseEntity.ok(updateSchedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteData(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.getReferenceById(id);

        if (schedule != null) {
            scheduleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
