package com.escalab.mediappbackend.service;
import com.escalab.mediappbackend.model.Consulta;

import java.util.List;


public interface ConsultaService extends ICRUD<Consulta> {
    byte[] generarReporte();
}
