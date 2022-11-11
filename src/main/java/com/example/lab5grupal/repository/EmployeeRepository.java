package com.example.lab5grupal.repository;

import com.example.lab5grupal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(nativeQuery = true, value = "select * from employees order by first_name")
    List<Employee> obtenerListaAscendente();

    @Query(nativeQuery = true, value = "select * from employees order by first_name desc")
    List<Employee> obtenerListaDescendente();


}
