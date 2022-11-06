package com.example.springbootdemo.dao;


import com.example.springbootdemo.bean.Doctor;
import com.example.springbootdemo.bean.Patient;
import com.example.springbootdemo.bean.Reminder;
import com.example.springbootdemo.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long>{

    //默认提供了Optional<User> findById(Long id);

//    Reminder findById(String Id);

//    @Query("select u from User u where u.id <= ?1")
//    Page<User> findMore(Long maxId, Pageable pageable);


    @Query("select r from Reminder r where r.id = ?1")
    Page<Reminder> getReminder(Long Id, Pageable pageable);

//    add Reminder
    @Modifying
    @Transactional
    //    @Query("insert into reminders (id, name, password, patient_id, reminder_id) values (?1, ?2, ?3, ?4, ?5)")
    @Query(value = "insert into reminders  (content, date, time, status, type, doctor_id, patient_id) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)",nativeQuery = true)
    int addReminder(String content, String date, String time, String status, String type, String doctor_id, String patient_id);

}
