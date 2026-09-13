package com.healthcare.model;

import java.time.LocalDateTime;

public class Department {

    private int departmentId;
    private String departmentName;
    private String location;
    private LocalDateTime createdAt;

    public Department() {
    }

    public Department(int departmentId, String departmentName,
                      String location, LocalDateTime createdAt) {

        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
        this.createdAt = createdAt;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}