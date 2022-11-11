package com.example.lab5grupal.controller;

import com.example.lab5grupal.repository.DepartmentRepository;
import com.example.lab5grupal.repository.EmployeeRepository;
import com.example.lab5grupal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/empleado")
public class EmployeesController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    //LISTA EMPLEADOS
    @GetMapping(value = {"", "lista"})
    public String listEmployee(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.findAll());
        return "employee/lista";
    }
    //LISTA EMPLEADOS ASCENDENTE (De la A a la Z)
    @GetMapping("/listaAscendente")
    public String listEmployeeAsc(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.obtenerListaAscendente());
        return "employee/lista";
    }
    //LISTA EMPLEADOS DESCENDENTE (De la Z a la A)
    @GetMapping("/listaDescendente")
    public String listEmployeeDes(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.obtenerListaDescendente());
        return "employee/lista";
    }

    @GetMapping("/info/{id}")
    public String Infoempleado(Model model, @PathVariable("id") Integer id){
        model.addAttribute("Employee",employeeRepository.findById(id));
        model.addAttribute("Department",departmentRepository.obtenerdepartment());
        model.addAttribute("Job",jobRepository.obtenerjobs());
        return "employee/information";
    }

}
