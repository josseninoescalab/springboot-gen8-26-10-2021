package com.escalab.mediappbackend.controller;

import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.service.PacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

  @Autowired
  private PacienteService pacienteService;

  @GetMapping
  public List<Paciente> findAll(){
    return pacienteService.findAll();
  }

  @GetMapping("/{id}")
  public Paciente findById(@PathVariable("id") Integer id){
    return pacienteService.findById(id);
  }

  @GetMapping("/name/{name}")
  public List<Paciente> findById(@PathVariable("name") String name){
    return pacienteService.findByName(name);
  }

  @PostMapping
  public @ResponseBody Paciente save(@RequestBody Paciente paciente){
    return pacienteService.save(paciente);
  }
}
