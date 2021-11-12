package com.escalab.mediappbackend.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer idConsulta;

  @Column(name = "fecha")
  private Date fecha;

  @ManyToOne
  @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
  private Paciente paciente;

  @ManyToOne
  @JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_especialidad"))
  private Especialidad especialidad;

  @ManyToOne
  @JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_medico"))
  private Medico medico;

  @OneToMany(mappedBy = "consulta", cascade = { CascadeType.ALL }, orphanRemoval = true)
  private List<DetalleConsulta> detalleConsulta;

  public Integer getIdConsulta() {
    return idConsulta;
  }

  public void setIdConsulta(Integer idConsulta) {
    this.idConsulta = idConsulta;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public Especialidad getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(Especialidad especialidad) {
    this.especialidad = especialidad;
  }

  public Medico getMedico() {
    return medico;
  }

  public void setMedico(Medico medico) {
    this.medico = medico;
  }

  public List<DetalleConsulta> getDetalleConsulta() {
    return detalleConsulta;
  }

  public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
    this.detalleConsulta = detalleConsulta;
  }
}
