package com.hospital.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Appointment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String patientName; private String doctorName; private LocalDate appointmentDate; private String reason; private String status="Booked";
 public Appointment(){}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getPatientName(){return patientName;} public void setPatientName(String v){patientName=v;}
 public String getDoctorName(){return doctorName;} public void setDoctorName(String v){doctorName=v;}
 public LocalDate getAppointmentDate(){return appointmentDate;} public void setAppointmentDate(LocalDate v){appointmentDate=v;}
 public String getReason(){return reason;} public void setReason(String v){reason=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
}
