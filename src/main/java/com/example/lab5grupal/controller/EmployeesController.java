package com.example.lab5grupal.controller;

import com.example.lab5grupal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class EmployeesController {

    @Autowired
    EmployeeRepository employeeRepository;

    //LISTA EMPLEADOS
    @GetMapping(value = {"", "/", "lista"})
    public String listEmployee(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.findAll());
        return "employee/lista";
    }
}
