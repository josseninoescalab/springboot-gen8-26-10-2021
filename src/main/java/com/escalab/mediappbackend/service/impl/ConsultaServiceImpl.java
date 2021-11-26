package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.dto.ConsultaDTO;
import com.escalab.mediappbackend.dto.ConsultaListaExamenDTO;
import com.escalab.mediappbackend.dto.ConsultaResumenDTO;
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
import java.util.Base64;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
			JasperPrint print = JasperFillManager.fillReport(
					file.getPath(), null,
					new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<ConsultaDTO> findAllDto() {
		List<ConsultaDTO> dtos = new ArrayList<>();
		List<Consulta> consultas = consultaRepository.findAll();
		consultas.forEach(consulta -> {
			ConsultaDTO consultaDTO = new ConsultaDTO();
			consultaDTO.setIdConsulta(consulta.getIdConsulta());
			consultaDTO.setMedico(consulta.getMedico());
			consultaDTO.setPaciente(consulta.getPaciente());
			consultaDTO.setNameMedico(consulta.getMedico().getNombres() + consulta.getMedico().getApellidos());
			consultaDTO.setNameEspecialidad(consulta.getEspecialidad().getNombre());
			dtos.add(consultaDTO);
		});

		for(Consulta c : consultas){
			ConsultaDTO consultaDTO = new ConsultaDTO();
			consultaDTO.setIdConsulta(c.getIdConsulta());
			consultaDTO.setMedico(c.getMedico());
			consultaDTO.setPaciente(c.getPaciente());
			dtos.add(consultaDTO);
		}
		return dtos;
	}

	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(det -> {
			det.setConsulta(dto.getConsulta());
		});
		consultaRepository.save(dto.getConsulta());
		dto.getLstExamen().forEach(ex -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), ex.getIdExamen()));
		return dto.getConsulta();
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultaRepository.listarResumen().forEach(x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

	@Override
	public String generarReportePDF() {
		byte[] data = this.generarReporte();
		return Base64.getEncoder().encodeToString(data);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


