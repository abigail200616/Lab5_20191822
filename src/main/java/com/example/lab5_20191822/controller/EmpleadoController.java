package com.example.lab5_20191822.controller;


import com.example.lab5_20191822.entity.Employees;
import com.example.lab5_20191822.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {


    final EmpleadoRepository empleadoRepository;


    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping("/list")
    public String listaEmpleados(Model model){
        List<Employees> lista = empleadoRepository.findAll();
        model.addAttribute("listaE", lista);
        return "/empleados";
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model, @RequestParam("id") int id) {

        Optional<Employees> optShipper = empleadoRepository.findById(id);

        if (optShipper.isPresent()) {
            empleadoRepository.deleteById(id);
        }
        return "redirect:/empleados/list";

    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model,
                                      @RequestParam("id") int id) {

        Optional<Employees> optEmployees = empleadoRepository.findById(id);

        if (optEmployees.isPresent()) {
            Employees employees = optEmployees.get();
            model.addAttribute("shipper", employees);
            return "editFrm";
        } else {
            return "redirect:/empleados/list";
        }
    }

    @PostMapping("/buscar")
    public String buscarEmpleadoPorNombre(@RequestParam("textoBuscar") String textoBuscar,
                                               Model model) {


        List<Employees> lista = empleadoRepository.buscarPorNombre(textoBuscar);
        model.addAttribute("shipperList", lista);
        model.addAttribute("textoBuscado", textoBuscar);

        return "/empleados";
    }







}
