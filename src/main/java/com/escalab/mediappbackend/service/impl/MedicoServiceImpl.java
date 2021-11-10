package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.repository.MedicoRepository;
import com.escalab.mediappbackend.service.MedicoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicoServiceImpl implements MedicoService {

  @Autowired
  private MedicoRepository medicoRepository;

  @Override
  public List<Medico> findAll() {
    return medicoRepository.findAll();
  }

  @Override
  public Medico findById(Integer id) {
    Optional<Medico> medico = medicoRepository.findById(id);
    if(medico.isPresent()){
      return medico.get();
    }else{
      throw new ModeloNotFoundException("Medico no encontrado");
    }
  }

  @Override
  public Medico save(Medico medico) {
    return medicoRepository.save(medico);
  }

  @Override
  public Medico update(Medico medico) {
    return save(medico);
  }

  @Override
  public boolean delete(Integer id) {
    medicoRepository.deleteById(id);
    return true;
  }
}
