package com.healthcare.model;

import java.time.LocalDate;

public class Doctor {

    private int doctorId;
    private int departmentId;
    private String doctorName;
    private String specialization;
    private String phone;
    private String email;
    private LocalDate joiningDate;
    private String status;

    public Doctor() {
    }

    public Doctor(int doctorId, int departmentId, String doctorName,
                  String specialization, String phone, String email,
                  LocalDate joiningDate, String status) {

        this.doctorId = doctorId;
        this.departmentId = departmentId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.joiningDate = joiningDate;
        this.status = status;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}