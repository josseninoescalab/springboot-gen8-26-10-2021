package com.escalab.mediappbackend.controller;

import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.service.PacienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @ApiOperation(value = "Obtener todos los pacientes",
      notes = "No necesita parametros de entrada",
      response = List.class,
      responseContainer = "Pacientes")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
      @ApiResponse(code = 404, message = "Not found, no encontrado"),
      @ApiResponse(code = 405, message = "No se encontraron pacientes en la BD"),
      @ApiResponse(code = 200, message = "Petición OK")})
  @GetMapping
  public ResponseEntity<List<Paciente>> findAll(){
    return new ResponseEntity<>(pacienteService.findAll(), HttpStatus.OK);
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

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<String> delete(
      @PathVariable("id") Integer id
  ){
    pacienteService.delete(id);
    return new ResponseEntity<>("Paciente elimando", HttpStatus.OK);
  }
}
