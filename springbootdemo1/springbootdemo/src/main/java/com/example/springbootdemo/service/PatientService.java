package com.example.springbootdemo.service;


import com.example.springbootdemo.bean.Doctor;
import com.example.springbootdemo.bean.Patient;
import com.example.springbootdemo.bean.Reminder;
import com.example.springbootdemo.dao.PatientRepository;
import com.example.springbootdemo.dao.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("patientService")
public class PatientService {
    @Autowired
    private PatientRepository PatientRepository;

    @Autowired ReminderRepository ReminderRepository;

    public Patient getPatientByID(Long id){
        return PatientRepository.findById(id).get();
    }

    public boolean isPatient(Long id, String password){
        if (PatientRepository.findById(id).get().getPassword().equals(password)){
            return true;
        }
        else {
            return false;
        }
    }

    public Integer addPatient(String name, String password, String doctor_id){
        Patient patient = new Patient(name, password, doctor_id);
        Patient p = PatientRepository.save(patient);
        Long id = p.getId();
        return id.intValue();
    }

    public Patient getPatientByIDAll(Long id){
        Patient patient = PatientRepository.findById(id).get();
        ArrayList<Integer> r_l1 = patient.getFinishedReminderList();
        ArrayList<Reminder> reminderList = new ArrayList<>();
        System.out.println(r_l1);
        for (int i = 0; i < r_l1.size(); i++){
            if (r_l1.get(i).equals(0)) {
                continue;
            }
            long x = r_l1.get(i).longValue();
            System.out.println(x);
            System.out.println(ReminderRepository.findById(x).get());
            reminderList.add(ReminderRepository.findById(x).get());
        }
        patient.setFinishedList(reminderList);

        ArrayList<Integer> r_l2 = patient.getUnfinishedReminderList();
        ArrayList<Reminder> reminderList2 = new ArrayList<>();
        for (int i = 0; i < r_l2.size(); i++){
            reminderList2.add(ReminderRepository.findById(r_l2.get(i).longValue()).get());
        }
        patient.setUnfinishedList(reminderList);
        return patient;
    }

}




