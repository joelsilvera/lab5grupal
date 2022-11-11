package com.example.lab5grupal.repository;

import com.example.lab5grupal.entity.Department;
import com.example.lab5grupal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(nativeQuery = true, value = "select * from departments")
    List<Department> obtenerdepartment();

    @Query(nativeQuery = true, value = "SELECT manager_id FROM hr.departments WHERE (`department_id` = ?1)")
    Integer Managxdepid(Integer depid);

}
