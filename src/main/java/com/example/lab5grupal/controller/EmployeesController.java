package com.example.lab5grupal.controller;

import com.example.lab5grupal.entity.Employee;
import com.example.lab5grupal.repository.DepartmentRepository;
import com.example.lab5grupal.repository.EmployeeRepository;
import com.example.lab5grupal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.Optional;

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
    public String informEmployee(Model model, @PathVariable("id") Integer id){
        Optional<Employee> optem= employeeRepository.findById(id);
        Employee emp = optem.get();
        model.addAttribute("Employee",emp);
        model.addAttribute("Department",departmentRepository.obtenerdepartment());
        model.addAttribute("Job",jobRepository.obtenerjobs());
        return "employee/information";
    }

    @PostMapping("guardar")
    public String saveEmployee(Employee Employee, RedirectAttributes attr){
        attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        System.out.println(Employee.getId());
        System.out.println(Employee.getFirstName());
        System.out.println(Employee.getEmail());
        employeeRepository.actualizarEmp(Employee.getFirstName(), Employee.getLastName(),
                Employee.getEmail(), Employee.getHiredate(), Employee.getJobid(), Employee.getSalary(), Employee.getDepartmentid(), Employee.getId());

        return "redirect:/empleado/lista";
    }

}
