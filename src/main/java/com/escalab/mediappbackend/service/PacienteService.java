package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.model.Paciente;
import java.util.List;

public interface PacienteService {

  List<Paciente> findAll();

  Paciente findById(Integer id);

  Paciente save(Paciente paciente);

  List<Paciente> findByName(String name);
}
