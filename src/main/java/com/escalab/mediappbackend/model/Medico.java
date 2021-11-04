package com.escalab.mediappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer idMedico;

  @Column(name = "nombres", length = 70)
  private String nombres;

  @Column(name = "apellidos", length = 70)
  private String apellidos;

  @Column(name = "CMP")
  private String cmp;

  @Column(name = "foto_url")
  private String foto_url;

  public Integer getIdMedico() {
    return idMedico;
  }

  public void setIdMedico(Integer idMedico) {
    this.idMedico = idMedico;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getCmp() {
    return cmp;
  }

  public void setCmp(String cmp) {
    this.cmp = cmp;
  }

  public String getFoto_url() {
    return foto_url;
  }

  public void setFoto_url(String foto_url) {
    this.foto_url = foto_url;
  }
}
