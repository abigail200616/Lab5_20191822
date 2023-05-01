package com.example.lab5_20191822.controller;


import com.example.lab5_20191822.dto.EmployeesDto;
import com.example.lab5_20191822.entity.Employees;
import com.example.lab5_20191822.repository.DepartmentsRepository;
import com.example.lab5_20191822.repository.EmpleadoRepository;
import com.example.lab5_20191822.repository.JobsRepository;
import com.example.lab5_20191822.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    final EmpleadoRepository empleadoRepository;
    @Autowired
    JobsRepository jobsRepository;
    @Autowired
    LocationsRepository locationsRepository;
    @Autowired
    DepartmentsRepository departmentsRepository;


    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping(value = {"/lista", ""})
    public String listaEmpleados(Model model){
        List<EmployeesDto> lista = empleadoRepository.listarEmpleados();
        model.addAttribute("listaE", lista);
        return "employee/empleados";
    }
    @PostMapping("/buscar")
    public String buscarEmpleado(@RequestParam("textoBuscar") String textoBuscar,
                                          Model model) {

        List<EmployeesDto> lista = empleadoRepository.buscarTexto(textoBuscar);
        model.addAttribute("listaE", lista);
        model.addAttribute("textoBuscado", textoBuscar);
        return "employee/empleados";
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model, @RequestParam("id") int id,RedirectAttributes attr) {

        Optional<Employees> optEmployee = empleadoRepository.findById(id);
        if (optEmployee.isPresent()) {
            empleadoRepository.deleteById(id);
        }
        attr.addFlashAttribute("msg","Empleado borrado exitosamente");
        return "redirect:/empleados/lista";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model,
                                      @RequestParam("id") int id) {

        Optional<Employees> optEmployees = empleadoRepository.findById(id);

        if (optEmployees.isPresent()) {
            Employees employees = optEmployees.get();
            model.addAttribute("listaPuesto", jobsRepository.findAll());
            model.addAttribute("listaDepart", departmentsRepository.findAll());
            model.addAttribute("listaCiudad", locationsRepository.findAll());
            model.addAttribute("employee", employees);
            return "employee/editar";
        } else {
            return "redirect:/empleados/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@RequestParam("employeeId") Integer employeeId,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("job") String job,
                                  @RequestParam("department") Integer department,
                                  @RequestParam("department.locations") Integer city,
                                  RedirectAttributes attr) {
        empleadoRepository.guardar(firstName, lastName , job , department, city, employeeId);
        attr.addFlashAttribute("msg","Empleado actualizado");
        return "redirect:/empleados/lista";
    }

    @GetMapping("/nuevoE")
    public String nuevoEmpleado(Model model) {
        model.addAttribute("listaPuesto", jobsRepository.findAll());
        model.addAttribute("listaJefes", empleadoRepository.findAll());
        model.addAttribute("listaDepart", departmentsRepository.findAll());

        return "employee/nuevo";
    }

    @PostMapping("/guardarNuevo")
    public String guardarEmpleado2(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   @RequestParam("job") String job,
                                   @RequestParam("salary") Double salary,
                                   @RequestParam("manager") Integer manager,
                                   @RequestParam("department") Integer department,
                                  RedirectAttributes attr) {
        empleadoRepository.guardarNuevo(firstName, lastName ,email, password, job , salary,
                                        manager, department);
        attr.addFlashAttribute("msg","Empleado creado exitosamente");
        return "redirect:/empleados/lista";
    }








}
