package com.escalab.mediappbackend.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer idUsuario;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "clave")
  private String password;

  @Column(name = "estado")
  private Boolean status;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "usuario_rol",
      joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
      inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
  private List<Rol> roles;

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public List<Rol> getRoles() {
    return roles;
  }

  public void setRoles(List<Rol> roles) {
    this.roles = roles;
  }
}
