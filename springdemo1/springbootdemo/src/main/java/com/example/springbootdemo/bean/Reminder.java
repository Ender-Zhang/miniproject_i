package com.example.springbootdemo.bean;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "reminders")
public class Reminder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private String reminder_date;

    private String reminder_time;

    private String reminder_type;

    private String reminder_status;

    private Long patient_id;

    private Long doctor_id;

    public Reminder(String content, String reminder_date, String reminder_time, String reminder_type, Integer patient_id, Integer doctor_id) {
        this.content = content;
        this.reminder_date = reminder_date;
        this.reminder_time = reminder_time;
        this.reminder_type = reminder_type;
        this.reminder_status = "0";
        this.patient_id = Long.valueOf(patient_id);
        this.doctor_id = Long.valueOf(doctor_id);
    }
}