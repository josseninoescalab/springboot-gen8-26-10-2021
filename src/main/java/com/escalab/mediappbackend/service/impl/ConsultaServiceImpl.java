package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Consulta;
import com.escalab.mediappbackend.model.Especialidad;
import com.escalab.mediappbackend.model.Medico;
import com.escalab.mediappbackend.model.Paciente;
import com.escalab.mediappbackend.repository.ConsultaExamenRepository;
import com.escalab.mediappbackend.repository.ConsultaRepository;
import com.escalab.mediappbackend.repository.PacienteRepository;
import com.escalab.mediappbackend.service.ConsultaService;
import com.escalab.mediappbackend.service.EspecialidadService;
import com.escalab.mediappbackend.service.MedicoService;
import com.escalab.mediappbackend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired	
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private EspecialidadService especialidadService;

	@Autowired
	private ConsultaExamenRepository ceRepo;
	
	@Override
	public Consulta save(Consulta obj) {
		obj.getDetalleConsulta().forEach(det -> {
			det.setConsulta(obj);
		});
		return consultaRepository.save(obj);
	}

	@Override
	public Consulta update(Consulta obj) {
		return consultaRepository.save(obj);
	}

	@Override
	public List<Consulta> findAll() {
		return consultaRepository.findAll();
	}

	@Override
	public Consulta findById(Integer id) {
		Optional<Consulta> op = consultaRepository.findById(id);
		if(!op.isPresent()) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return op.get();
	}

	@Override
	public boolean delete(Integer id) {
		Optional<Consulta> obj = consultaRepository.findById(id);
		if(obj.isPresent()) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else{
		consultaRepository.deleteById(id);
		return true;
		}
	}

	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();
			/*JasperPrint print = JasperFillManager.fillReport(
					file.getPath(), null,
					new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


