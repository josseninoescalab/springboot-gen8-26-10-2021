package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.repository.PacienteRepository;
import com.escalab.mediappbackend.service.PacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

  private final PacienteRepository pacienteRepository;

  @Autowired
  public PacienteServiceImpl(PacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }


  @Override
  public List<Paciente> findAll() {
    return pacienteRepository.findAll();
  }

  @Override
  public Paciente findById(Integer id) {
    Optional<Paciente> op = pacienteRepository.findById(id);
    return op.isPresent() ? op.get() : new Paciente();
  }

  @Override
  public Paciente save(Paciente paciente) {
    return pacienteRepository.save(paciente);
  }

  @Override
  public List<Paciente> findByName(String name) {
    return pacienteRepository.findAllByNombres(name);
  }
}
