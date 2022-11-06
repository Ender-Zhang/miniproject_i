package com.example.springbootdemo.dao;


import com.example.springbootdemo.bean.Doctor;
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
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    //默认提供了Optional<User> findById(Long id);

    Doctor findById(String Id);

//    @Query("select u from User u where u.id <= ?1")
//    Page<User> findMore(Long maxId, Pageable pageable);

    @Query("select d from Doctor d where d.id = ?1")
    Page<Doctor> isDoctor(Long Id, String password, Pageable pageable);

    @Query("select d from Doctor d where d.id = ?1")
    Page<Doctor> getDoctor(Long Id, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int updateById(String name, Long id);

//    增加医生
    @Modifying
    @Transactional
//    @Query("insert into doctors (id, name, password, patient_id, reminder_id) values (?1, ?2, ?3, ?4, ?5)")
    @Query(value = "insert into doctors (name, password, patient_id, reminder_id) values (?1, ?2, '1', '1')",nativeQuery = true)
    int addDoctor(String name, String password);

//   更改reminder_id
    @Modifying
    @Transactional
    @Query(value = "update doctors set reminder_low = ?1 where id = ?2",nativeQuery = true)
    int updateLowReminderId(String reminder_id, String id);

    @Modifying
    @Transactional
    @Query(value = "update doctors set reminder_middle = ?1 where id = ?2",nativeQuery = true)
    int updateMidReminderId(String reminder_id, String id);

    @Modifying
    @Transactional
    @Query(value = "update doctors set reminder_hige = ?1 where id = ?2",nativeQuery = true)
    int updateHighReminderId(String reminder_id, String id);
////    @Query("insert into doctors (id, name, password, patient_id, reminder_id) values (?1, ?2, ?3, ?4, ?5)")
//    @Query(value = "insert into doctors (name, password, patient_id, reminder_id) values (?1, ?2, '1', '1')"",nativeQuery = true)
//    int addDoctor(String name, String password);
}
