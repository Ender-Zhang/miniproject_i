package com.example.springbootdemo.bean;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "doctors")
public class Doctor implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

//    @Transient
//    private ArrayList<Integer> patient_Id;
//
//    @Transient
//    private ArrayList<Integer> reminder_Id;

    private String patient_Id;

    private String reminder_Id;

    private String reminder_high;

    private String reminder_low;

    private String reminder_middle;

    @Transient
    private ArrayList<Patient> patientList;

    @Transient
    private ArrayList<Reminder> reminderList;

    public Doctor(String name, String password) {
        this.name = name;
        this.password = password;
        this.patient_Id = "[NULL]";
        this.reminder_Id = "[NULL]";
        this.reminder_high = "[NULL]";
        this.reminder_low = "[NULL]";
        this.reminder_middle = "[NULL]";
    }

    public ArrayList<Integer> getReminderIdList(){
        ArrayList<Integer> reminderIdList = new ArrayList<>();
        String[] reminderIdStringList = this.reminder_Id.replace("[","").replace("]","").split(", ");
        for (String reminderIdString : reminderIdStringList){
            reminderIdList.add(Integer.valueOf(reminderIdString.trim()));
        }
        return reminderIdList;
    }

    public ArrayList<Integer> getReminderHighList(){
        ArrayList<Integer> reminderHighList = new ArrayList<>();
        String[] reminderHighStringList = this.reminder_high.replace("[","").replace("]","").split(", ");
        for (String reminderHighString : reminderHighStringList){
            reminderHighList.add(Integer.valueOf(reminderHighString.trim()));
        }
        return reminderHighList;
    }

    public ArrayList<Integer> getReminderLowList(){
        ArrayList<Integer> reminderLowList = new ArrayList<>();
        String[] reminderLowStringList = this.reminder_low.replace("[","").replace("]","").split(", ");
        for (String reminderLowString : reminderLowStringList){
            reminderLowList.add(Integer.valueOf(reminderLowString.trim()));
        }
        return reminderLowList;
    }

    public ArrayList<Integer> getReminderMiddleList(){
        ArrayList<Integer> reminderMiddleList = new ArrayList<>();
        String[] reminderMiddleStringList = this.reminder_middle.replace("[","").replace("]","").split(", ");
        for (String reminderMiddleString : reminderMiddleStringList){
            reminderMiddleList.add(Integer.valueOf(reminderMiddleString.trim()));
        }
        return reminderMiddleList;
    }

    public ArrayList<Integer> getPatientIdList(){
        ArrayList<Integer> patientIdList = new ArrayList<>();
        String[] patientIdStringList = this.patient_Id.replace("[","").replace("]","").split(", ");
        for (String patientIdString : patientIdStringList){
            if (!patientIdString.equals("NULL")){
                patientIdList.add(Integer.valueOf(patientIdString.trim()));
            }
//            patientIdList.add(Integer.valueOf(patientIdString));
        }
        return patientIdList;
    }

    public ArrayList<Integer> getReminderIdList(String type){
        ArrayList<Integer> reminderIdList = new ArrayList<>();
        String[] reminderIdStringList = this.reminder_Id.replace("[","").replace("]","").split(", ");
        for (int i = 0; i < reminderIdStringList.length; i++){
            if (reminderIdStringList[i].equals(type)){
                reminderIdList.add(Integer.valueOf(reminderIdStringList[i].trim()));
            }
        }
        return reminderIdList;
    }
    public void setPatientList(ArrayList<Patient> patientList){
        this.patientList = patientList;
    }

    public void setReminderList(ArrayList<Reminder> reminderList){
        this.reminderList = reminderList;
    }
}