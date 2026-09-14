package com.healthcare.model;

public class DepartmentClinicalStatistic {

    private int departmentId;
    private String departmentName;
    private int totalDoctors;
    private int totalPatients;
    private int totalAppointments;
    private int completedAppointments;
    private int scheduledAppointments;
    private int totalEncounters;

    public DepartmentClinicalStatistic() {
    }

    public DepartmentClinicalStatistic(
            int departmentId,
            String departmentName,
            int totalDoctors,
            int totalPatients,
            int totalAppointments,
            int completedAppointments,
            int scheduledAppointments,
            int totalEncounters) {

        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.totalDoctors = totalDoctors;
        this.totalPatients = totalPatients;
        this.totalAppointments = totalAppointments;
        this.completedAppointments = completedAppointments;
        this.scheduledAppointments = scheduledAppointments;
        this.totalEncounters = totalEncounters;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(int totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getCompletedAppointments() {
        return completedAppointments;
    }

    public void setCompletedAppointments(int completedAppointments) {
        this.completedAppointments = completedAppointments;
    }

    public int getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(int scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    public int getTotalEncounters() {
        return totalEncounters;
    }

    public void setTotalEncounters(int totalEncounters) {
        this.totalEncounters = totalEncounters;
    }
}