package com.escalab.mediappbackend.dto;

import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.model.Paciente;
import java.io.Serializable;
import org.springframework.hateoas.ResourceSupport;

public class ConsultaDTO extends ResourceSupport implements Serializable  {
  
  private Integer idConsulta;
  private String nameMedico;
  private String nameEspecialidad;
  private Medico medico;
  private Paciente paciente;

  public Integer getIdConsulta() {
    return idConsulta;
  }

  public void setIdConsulta(Integer idConsulta) {
    this.idConsulta = idConsulta;
  }

  public Medico getMedico() {
    return medico;
  }

  public void setMedico(Medico medico) {
    this.medico = medico;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public String getNameMedico() {
    return nameMedico;
  }

  public void setNameMedico(String nameMedico) {
    this.nameMedico = nameMedico;
  }

  public String getNameEspecialidad() {
    return nameEspecialidad;
  }

  public void setNameEspecialidad(String nameEspecialidad) {
    this.nameEspecialidad = nameEspecialidad;
  }
}
