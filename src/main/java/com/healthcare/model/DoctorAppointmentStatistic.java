package com.healthcare.model;

public class DoctorAppointmentStatistic {

    private int doctorId;
    private String doctorName;
    private String specialization;
    private int totalAppointments;

    public DoctorAppointmentStatistic() {
    }

    public DoctorAppointmentStatistic(
            int doctorId,
            String doctorName,
            String specialization,
            int totalAppointments) {

        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.totalAppointments = totalAppointments;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }
}