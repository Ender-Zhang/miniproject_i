package com.example.springbootdemo.controller;



import com.example.springbootdemo.bean.Doctor;
import com.example.springbootdemo.bean.Patient;
import com.example.springbootdemo.bean.Reminder;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.DoctorRepository;
import com.example.springbootdemo.service.DoctorService;
import com.example.springbootdemo.service.PatientService;
import com.example.springbootdemo.service.ReminderService;
import com.example.springbootdemo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class ApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ReminderService reminderService;

    @GetMapping("/init")
    public String init() {
//        User user = null;
//        for(int i=0;i<10;i++){
//            user = new User();
//            user.setName("test"+i);
//            userService.save(user);
//        }
        return "初始化完成。";
    }

    @GetMapping("/userByName/{username}")
    public User getUserByName(@PathVariable("username") String username) {
        return userService.getByName(username);
    }

    @GetMapping("/userById/{userid}")
    public User getUserById(@PathVariable("userid") Long userid) {
        return userService.getUserByID(userid);
    }

    @GetMapping("/page")
    public Page<User> getPage() {
        return userService.findPage();
    }

    @GetMapping("/page/{maxID}")
    public Page<User> getPageByMaxID(@PathVariable("maxID") Long maxID) {
        return userService.find(maxID);
    }

    @RequestMapping("/update/{id}/{name}")
    public User update(@PathVariable Long id, @PathVariable String name) {
        return userService.update(id, name);
    }

    @RequestMapping("/update/{id}")
    public Boolean updateById(@PathVariable Long id) {
        return userService.updateById("newName", id);
    }

    @RequestMapping("/isDoctor")
    @ResponseBody
    public Boolean isDoctor(@RequestParam Long id, @RequestParam String password) {
//        return userService.isDoctor(id);
        return doctorService.isDoctor(id, password);
//        return true;
    }

    @RequestMapping("/doctor/{id}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable Integer id) {
        return doctorService.getDoctorByIDAll(Long.valueOf(id));
    }

    @PostMapping("/doctor")
    @ResponseBody
    public Integer addDoctor(@RequestParam String name, @RequestParam String password) {
        return doctorService.addDoctor(name, password);
//        return doctorService.save(name, password);
    }

    @RequestMapping("/patient/{id}")
    @ResponseBody
    public Patient getPatient(@PathVariable Long id) {
//        return patientService.getPatientByID(id);
        return patientService.getPatientByIDAll(id);
    }

    @PostMapping("/patient")
    @ResponseBody
    public Integer addPatient(@RequestParam String name, @RequestParam String password, @RequestParam String doctor_id) {
        return patientService.addPatient(name, password, doctor_id);
    }

    @RequestMapping("/reminder/{id}")
    @ResponseBody
    public Reminder getReminder(@PathVariable Long id) {
        return reminderService.getReminderByID(id);
    }

    @PostMapping("/reminder")
    @ResponseBody
    public Integer addReminder(@RequestParam String content, @RequestParam String date, @RequestParam String time, @RequestParam String type, @RequestParam Integer patient_id, @RequestParam Integer doctor_id) {
        return reminderService.addReminder(content, date, time, type, patient_id, doctor_id);
    }

    @PostMapping("/reminderm/{id}")
    @ResponseBody
    public boolean modifyReminder(@PathVariable Long id) {
        return reminderService.modifyReminder(id);
    }
}

