package com.escalab.mediappbackend.repository;

import com.escalab.mediappbackend.model.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

  // select * from paciente where nombres = ''
  List<Paciente> findAllByNombres(String nombres);

}
