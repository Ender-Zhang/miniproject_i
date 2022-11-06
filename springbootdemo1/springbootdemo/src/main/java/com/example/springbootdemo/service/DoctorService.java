package com.example.springbootdemo.service;


import com.example.springbootdemo.bean.Doctor;
import com.example.springbootdemo.bean.Patient;
import com.example.springbootdemo.bean.Reminder;
import com.example.springbootdemo.dao.DoctorRepository;
import com.example.springbootdemo.dao.PatientRepository;
import com.example.springbootdemo.dao.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Service("doctorService")
public class DoctorService {
    @Autowired
    private DoctorRepository DoctorRepository;

    @Autowired PatientRepository PatientRepository;

    @Autowired ReminderRepository ReminderRepository;

    public Doctor getDoctorByID(Long id){
        return DoctorRepository.findById(id).get();
    }


    public boolean isDoctor(Long id, String password){
        if (DoctorRepository.findById(id).get().getPassword().equals(password)){
            return true;
        }
        else {
            return false;
        }
//        return DoctorRepository.isDoctor(id, password);
    }


    public Integer addDoctor(String name, String password){
//        DoctorRepository.save(doctor);
//        DoctorRepository.addDoctor(name, password);
//        String n = DoctorRepository.save(new Doctor(name, password)).getName();
        Doctor doctor = new Doctor(name, password);
        Doctor d = DoctorRepository.save(doctor);
//        DoctorRepository.flush();
//        d = DoctorRepository.saveAndFlush(d);
//        Integer id = d.getId();
        Long id = d.getId();
        return id.intValue();
    }

    public Doctor getDoctorByIDAll(Long id){
        Doctor doctor = DoctorRepository.findById(id).get();
        ArrayList<Integer> p_l = doctor.getPatientIdList();
        ArrayList<Patient> patientList = new ArrayList<>();
        for (int i = 0; i < p_l.size(); i++){
            patientList.add(PatientRepository.findById(p_l.get(i).longValue()).get());
        }
        doctor.setPatientList(patientList);

        ArrayList<Integer> r_l = doctor.getReminderIdList();
        ArrayList<Reminder> reminderList = new ArrayList<>();
        for (int i = 0; i < r_l.size(); i++){
            reminderList.add(ReminderRepository.findById(r_l.get(i).longValue()).get());
        }
        doctor.setReminderList(reminderList);
        return doctor;
    }

}



