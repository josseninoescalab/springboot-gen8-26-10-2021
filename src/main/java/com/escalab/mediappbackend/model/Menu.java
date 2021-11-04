package com.escalab.mediappbackend.model;

import java.util.List;
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
@Table(name = "menu")
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer idMenu;
  private String nombre;
  private String url;
  private String icono;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "menu_rol",
      joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
      inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
  private List<Rol> roles;

  public Integer getIdMenu() {
    return idMenu;
  }

  public void setIdMenu(Integer idMenu) {
    this.idMenu = idMenu;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIcono() {
    return icono;
  }

  public void setIcono(String icono) {
    this.icono = icono;
  }

  public List<Rol> getRoles() {
    return roles;
  }

  public void setRoles(List<Rol> roles) {
    this.roles = roles;
  }
}
