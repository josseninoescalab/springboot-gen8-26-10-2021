package com.escalab.mediappbackend.service;
import com.escalab.mediappbackend.dto.ConsultaDTO;
import com.escalab.mediappbackend.dto.ConsultaListaExamenDTO;
import com.escalab.mediappbackend.model.Consulta;

import java.util.List;


public interface ConsultaService extends ICRUD<Consulta> {
    byte[] generarReporte();

  List<ConsultaDTO> findAllDto();

  Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO);
}
