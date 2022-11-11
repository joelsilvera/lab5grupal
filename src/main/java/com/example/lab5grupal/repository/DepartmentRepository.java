package com.example.lab5grupal.repository;

import com.example.lab5grupal.entity.Department;
import com.example.lab5grupal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(nativeQuery = true, value = "select * from departments")
    List<Department> obtenerdepartment();

}
