package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
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
    if(op.isPresent()){
      return op.get();
    }else{
      throw new ModeloNotFoundException("Paciente no encontrado");
    }
  }

  @Override
  public Paciente save(Paciente paciente) {
    return pacienteRepository.save(paciente);
  }

  @Override
  public Paciente update(Paciente paciente) {
    return save(paciente);
  }

  @Override
  public boolean delete(Integer id) {
    pacienteRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Paciente> findByName(String name) {
    return pacienteRepository.findAllByNombres(name);
  }
}
