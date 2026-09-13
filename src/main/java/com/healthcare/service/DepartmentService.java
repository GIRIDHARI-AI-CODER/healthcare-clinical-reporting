package com.healthcare.service;

import com.healthcare.model.Department;
import com.healthcare.repository.DepartmentRepository;

import java.util.List;

public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService() {
        this.departmentRepository = new DepartmentRepository();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(int departmentId) {

        List<Department> departments =
                departmentRepository.findAll();

        for (Department department : departments) {
            if (department.getDepartmentId() == departmentId) {
                return department;
            }
        }

        return null;
    }
}