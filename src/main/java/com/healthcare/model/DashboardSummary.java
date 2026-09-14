package com.healthcare.model;

public class DashboardSummary {

    private int totalPatients;
    private int totalDoctors;
    private int totalAppointments;
    private int scheduledAppointments;
    private int completedAppointments;

    public DashboardSummary() {
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(int totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(int scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    public int getCompletedAppointments() {
        return completedAppointments;
    }

    public void setCompletedAppointments(int completedAppointments) {
        this.completedAppointments = completedAppointments;
    }
}