package com.escalab.mediappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paciente")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id_paciente")
  private Integer idPaciente;

  @Column(name = "nombres", length = 70)
  @Size(min = 3, max = 70, message = "El nombre debe tener un valor mayor a 3 digitos y menor a 70")
  private String nombres;

  @Column(name = "apellidos", length = 70)
  private String apellidos;

  @Column(name = "dni", length = 15)
  @Size(min = 8, max = 15, message = "El DNI debe tener un valor mayor a 8 digitos y menos a 15 digitos")
  private String dni;

  @Column(name = "direccion", length = 255)
  private String direccion;

  @Column(name = "telefono", length = 20)
  private String telefono;

  @Column(name = "email")
  @Email(message = "Debe ser un email valido")
  private String email;

  public Integer getIdPaciente() {
    return idPaciente;
  }

  public void setIdPaciente(Integer idPaciente) {
    this.idPaciente = idPaciente;
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

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
