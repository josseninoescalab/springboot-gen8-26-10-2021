package com.escalab.mediappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer idRol;

  @Column(name = "nombre")
  private String nombre;

}
