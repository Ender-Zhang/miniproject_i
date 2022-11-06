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
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

@Service("reminderService")
public class ReminderService {
    @Autowired
    private ReminderRepository ReminderRepository;

    @Autowired DoctorRepository DoctorRepository;

    @Autowired PatientRepository PatientRepository;

    public Reminder getReminderByID(Long id){
        return ReminderRepository.findById(id).get();
    }


    public Integer addReminder(String content, String reminder_date, String reminder_time, String reminder_type, Integer patient_id, Integer doctor_id){

        if (reminder_type.equals("1")){
            Reminder reminder = new Reminder(content, reminder_date, reminder_time, reminder_type, patient_id, doctor_id);
            Reminder r = ReminderRepository.saveAndFlush(reminder);
            Long id = r.getId();
            Doctor d = DoctorRepository.findById(doctor_id.longValue()).get();
            ArrayList<Integer> a = d.getReminderLowList();
            a.add(id.intValue());
            DoctorRepository.updateLowReminderId(a.toString(), doctor_id.toString());
            Patient p = PatientRepository.findById(patient_id.longValue()).get();
            ArrayList<Integer> b = p.getUnfinishedReminderList();
            b.add(id.intValue());
            PatientRepository.updateUnfinishedReminderId(b.toString(), patient_id.toString());
            return id.intValue();
        }

        if (reminder_type.equals("2")){
            Reminder reminder = new Reminder(content, reminder_date, reminder_time, reminder_type, patient_id, doctor_id);
            Reminder r = ReminderRepository.saveAndFlush(reminder);
            Long id = r.getId();
            Doctor d = DoctorRepository.findById(doctor_id.longValue()).get();
            ArrayList<Integer> a = d.getReminderMiddleList();
            a.add(id.intValue());
            DoctorRepository.updateMidReminderId(a.toString(), doctor_id.toString());
            Patient p = PatientRepository.findById(patient_id.longValue()).get();
            ArrayList<Integer> b = p.getUnfinishedReminderList();
            b.add(id.intValue());
            PatientRepository.updateUnfinishedReminderId(b.toString(), patient_id.toString());
            return id.intValue();
        }

        if (reminder_type.equals("3")){
            Reminder reminder = new Reminder(content, reminder_date, reminder_time, reminder_type, patient_id, doctor_id);
            Reminder r = ReminderRepository.saveAndFlush(reminder);
            Long id = r.getId();
            Doctor d = DoctorRepository.findById(doctor_id.longValue()).get();
            ArrayList<Integer> a = d.getReminderHighList();
            a.add(id.intValue());
            DoctorRepository.updateHighReminderId(a.toString(), doctor_id.toString());
            Patient p = PatientRepository.findById(patient_id.longValue()).get();
            ArrayList<Integer> b = p.getUnfinishedReminderList();
            b.add(id.intValue());
            PatientRepository.updateUnfinishedReminderId(b.toString(), patient_id.toString());
            return id.intValue();
        }

       return 0;
    }

    public boolean modifyReminder(Long id) {
        try {
            Reminder reminder = ReminderRepository.findById(id).get();
            reminder.setReminder_status("1");
            ReminderRepository.saveAndFlush(reminder);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

