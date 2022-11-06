package com.example.springbootdemo.dao;


import com.example.springbootdemo.bean.Doctor;
import com.example.springbootdemo.bean.Patient;
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
public interface PatientRepository extends JpaRepository<Patient, Long>{

    //默认提供了Optional<User> findById(Long id);

//    Patient findById(String Id);

//    @Query("select u from User u where u.id <= ?1")
//    Page<User> findMore(Long maxId, Pageable pageable);

    @Query("select p from Patient  p where p.id = ?1")
    Page<Patient> isPatient(Long Id, String password, Pageable pageable);

    @Query("select p from Patient p where p.id = ?1")
    Page<Patient> getPatient(Long Id, Pageable pageable);

//    add patient

    @Modifying
    @Transactional
    //    @Query("insert into doctors (id, name, password, patient_id, reminder_id) values (?1, ?2, ?3, ?4, ?5)")
    @Query(value = "insert into patients  (name, password, doctor_id) values (?1, ?2, ?3)",nativeQuery = true)
    int addPatient(String name, String password, String doctor_id);

    @Modifying
    @Transactional
    @Query(value = "update patients set unfinished_reminder_list = ?1 where id = ?2",nativeQuery = true)
    int updateUnfinishedReminderId(String reminder_id, String id);
}
