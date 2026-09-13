package com.healthcare;

import com.healthcare.model.Department;
import com.healthcare.repository.DepartmentRepository;

import java.util.List;

public class DepartmentRepositoryTest {

    public static void main(String[] args) {

        DepartmentRepository repository =
                new DepartmentRepository();

        List<Department> departments =
                repository.findAll();

        System.out.println("Total departments: " + departments.size());

        for (Department department : departments) {

            System.out.println(
                    department.getDepartmentId() + " | " +
                    department.getDepartmentName() + " | " +
                    department.getLocation() + " | " +
                    department.getCreatedAt()
            );
        }
    }
}