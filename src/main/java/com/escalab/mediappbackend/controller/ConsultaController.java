package com.escalab.mediappbackend.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.escalab.mediappbackend.dto.ConsultaDTO;
import com.escalab.mediappbackend.dto.ConsultaListaExamenDTO;
import com.escalab.mediappbackend.dto.ConsultaResumenDTO;
import com.escalab.mediappbackend.model.Consulta;
import com.escalab.mediappbackend.service.ArchivoService;
import com.escalab.mediappbackend.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService service;

	@Autowired
	private ArchivoService serviceArchivo;

	@GetMapping
	public ResponseEntity<List<Consulta>> findAll(){
		List<Consulta> lista = service.findAll();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id) {
		Consulta obj = service.findById(id);
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta consulta) {
		Consulta obj = service.update(consulta);
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping(value = "/leerArchivo/{idArchivo}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {

		byte[] arr = serviceArchivo.leerArchivo(idArchivo);

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}

	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(){
		byte[] data = null;
		data = service.generarReporte();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@GetMapping("/dto")
	public List<ConsultaDTO> findAllDto(){
		List<ConsultaDTO> dtos = new ArrayList<>();
		List<Consulta> consultas = service.findAll();
		consultas.forEach(consulta -> {
			ConsultaDTO d = new ConsultaDTO();
			d.setIdConsulta(consulta.getIdConsulta());
			d.setNameMedico(consulta.getMedico().getNombres() + consulta.getMedico().getApellidos());
			d.setNameEspecialidad(consulta.getEspecialidad().getNombre());
			// localhost:0880/paciente/1
			ControllerLinkBuilder linkTo1 =
					linkTo(methodOn(PacienteController.class).findById((consulta.getPaciente().getIdPaciente())));
			d.add(linkTo1.withSelfRel());

			// localhost:8080/medico/11
			ControllerLinkBuilder linkTo2 =
					linkTo(methodOn(MedicoController.class).listarPorId((consulta.getMedico().getIdMedico())));
			d.add(linkTo2.withSelfRel());

			// localhost:8080/consultas/1
			ControllerLinkBuilder linkTo = linkTo(methodOn(ConsultaController.class).listarPorId((consulta.getIdConsulta())));
			d.add(linkTo.withSelfRel());

			dtos.add(d);
		});
		return dtos;
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaDTO) {
		Consulta obj = service.registrarTransaccional(consultaDTO);
		//consultas/4
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}


	@GetMapping(value = "/generar/sql")
	public ResponseEntity<List<ConsultaResumenDTO>> generarSql(){
		List<ConsultaResumenDTO> data = service.listarResumen();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/generarReporte/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<String> generarReportePDF(){
		String data = null;
		data = service.generarReportePDF();
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
}

