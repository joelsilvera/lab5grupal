package com.example.lab5grupal.repository;

import com.example.lab5grupal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(nativeQuery = true, value = "select * from employees order by first_name")
    List<Employee> obtenerListaAscendente();

    @Query(nativeQuery = true, value = "select * from employees order by first_name desc")
    List<Employee> obtenerListaDescendente();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `hr`.`employees` SET \n" +
            "`first_name` = ?1,\n" +
            " `last_name` = ?2, \n" +
            " `email` = ?3, \n" +
            " `hire_date` = ?4, \n" +
            " `job_id` = ?5, \n" +
            " `salary` = ?6, \n" +
            " `department_id` = ?7 WHERE (`employee_id` = ?8)")
    void actualizarEmp(String nombre, String apellido, String email, String fecha, String jobid, BigDecimal salario, Integer depid, Integer id);


}
